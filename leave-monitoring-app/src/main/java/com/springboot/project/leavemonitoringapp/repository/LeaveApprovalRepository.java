package com.springboot.project.leavemonitoringapp.repository;

import com.springboot.project.leavemonitoringapp.model.LeaveApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveApprovalRepository extends JpaRepository<LeaveApproval, Long> {
    @Query(value = "SELECT ROWNUM SRL_NUM,\n" +
            "        NTF_RESPONDER,\n" +
            "        CASE\n" +
            "           WHEN APPROVER_NAME IS NULL THEN NTF_RESPONDER\n" +
            "           ELSE APPROVER_NAME\n" +
            "        END\n" +
            "           APPROVER_NAME,\n" +
            "        APPROVER_ACTION,\n" +
            "        NTF_NOTE NOTE,\n" +
            "        ACTION_DATE\n" +
            "   FROM                                                                     --\n" +
            "       (                                                                    --\n" +
            "        SELECT  SRL_NUM,\n" +
            "                ACTION_DATE,\n" +
            "                NTF_RESPONDER,\n" +
            "                APPROVER_NAME,\n" +
            "                APPROVER_ACTION,\n" +
            "                NTF_NOTE\n" +
            "           FROM                                                             --\n" +
            "               (  SELECT X.SRL_NUM SRL_NUM,\n" +
            "                         X.NTF_RESPONDER,\n" +
            "                         CASE\n" +
            "                            WHEN PPF.FULL_NAME IS NULL\n" +
            "                            THEN\n" +
            "                               (SELECT DISPLAY_NAME\n" +
            "                                  FROM APPS.WF_ROLES\n" +
            "                                 WHERE NAME = X.NTF_RESPONDER)\n" +
            "                            ELSE\n" +
            "                               PPF.FULL_NAME\n" +
            "                         END\n" +
            "                            AS APPROVER_NAME,\n" +
            "                         INITCAP (X.NTF_ACTION) AS APPROVER_ACTION,\n" +
            "                         REPLACE (REPLACE (X.NTF_NOTE, CHR (13), ' '),\n" +
            "                                  CHR (10),\n" +
            "                                  '')\n" +
            "                            AS NTF_NOTE,\n" +
            "                         TO_CHAR (X.CREATION_DATE, 'DD-MON-RR HH24:MI')\n" +
            "                            AS ACTION_DATE\n" +
            "                    FROM                          --xxssgil_er_approval_hist x\n" +
            "                        XXSSGIL.XXSSGIL_APPROVAL_HISTORYS_VV X,\n" +
            "                         APPS.PER_ALL_PEOPLE_F PPF\n" +
            "                   WHERE X.EMPLOYEE_ID = PPF.PERSON_ID(+)\n" +
            "                         AND TRUNC (X.CREATION_DATE) BETWEEN PPF.EFFECTIVE_START_DATE(+)\n" +
            "                                                         AND PPF.EFFECTIVE_END_DATE(+)\n" +
            "                         AND X.EXPENSE_REPORT_HDR_ID = :L_INVOICE_ID\n" +
            "                ORDER BY X.SRL_NUM)\n" +
            "        UNION ALL\n" +
            "        (SELECT SRL_NUM,\n" +
            "                ACTION_DATE,\n" +
            "                NTF_RESPONDER,\n" +
            "                APPROVER_NAME,\n" +
            "                APPROVER_ACTION,\n" +
            "                NTF_NOTE\n" +
            "           FROM (SELECT 0 AS SRL_NUM,\n" +
            "                       WUR.USER_NAME AS NTF_RESPONDER,\n" +
            "                        (SELECT PAPF.FULL_NAME\n" +
            "                           FROM APPS.PER_ALL_PEOPLE_F PAPF,\n" +
            "                                APPS.PER_ALL_ASSIGNMENTS_F PAAF\n" +
            "                          WHERE     1 = 1\n" +
            "                                AND PAPF.BUSINESS_GROUP_ID = '84'\n" +
            "                                AND PAPF.EMPLOYEE_NUMBER = WUR.USER_NAME --'3023'\n" +
            "                                AND PAPF.PERSON_ID = PAAF.PERSON_ID\n" +
            "                                AND TRUNC (SYSDATE) BETWEEN PAPF.EFFECTIVE_START_DATE\n" +
            "                                                        AND PAPF.EFFECTIVE_END_DATE\n" +
            "                                AND TRUNC (SYSDATE) BETWEEN PAAF.EFFECTIVE_START_DATE\n" +
            "                                                        AND PAAF.EFFECTIVE_END_DATE)\n" +
            "                           AS APPROVER_NAME,\n" +
            "                        'Pending' AS APPROVER_ACTION,\n" +
            "                        '' AS NTF_NOTE,\n" +
            "                        '' AS ACTION_DATE\n" +
            "                   FROM APPS.XXSSGIL_APPROVAL_LV_HDR H,\n" +
            "                        APPS.XXSSGIL_APPROVAL_LV_LINE L,\n" +
            "                        APPS.WF_USER_ROLES WUR,\n" +
            "                        APPS.WF_NOTIFICATIONS N\n" +
            "                  WHERE     1 = 1\n" +
            "                        AND H.REPORT_HEADER_ID = :L_INVOICE_ID\n" +
            "                        AND WUR.ROLE_NAME = N.TO_USER\n" +
            "                        AND H.WF_ITEM_KEY = N.ITEM_KEY\n" +
            "                        AND L.REPORT_HEADER_ID = H.REPORT_HEADER_ID\n" +
            "                        AND N.STATUS = 'OPEN'\n" +
            "                        AND H.STATUS_FLG = 'In-Process')))\n" +
            "                        order by SRL_NUM desc\n" +
            "                        FETCH FIRST 1 ROW ONLY",nativeQuery = true)
    List<LeaveApproval> findLeaveApprovalRecord(@Param("L_INVOICE_ID") String headerId); //here L_INVOICE_ID is replace by report header id
}
