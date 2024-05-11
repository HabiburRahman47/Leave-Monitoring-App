package com.springboot.project.leavemonitoringapp.repository;

import com.springboot.project.leavemonitoringapp.model.LeaveApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveApprovalRepository extends JpaRepository<LeaveApproval, Long> {
    @Query(value = "SELECT X.SRL_NUM AS SRL_NUM,\n" +
            "         X.EXPENSE_REPORT_HDR_ID REPORT_HEADER_ID,\n" +
            "       X.NTF_RESPONDER,\n" +
            "       CASE\n" +
            "           WHEN PPF.FULL_NAME IS NULL\n" +
            "               THEN (SELECT DISPLAY_NAME\n" +
            "                       FROM APPS.WF_ROLES\n" +
            "                       WHERE NAME = X.NTF_RESPONDER)\n" +
            "           ELSE\n" +
            "               PPF.FULL_NAME\n" +
            "       END AS APPROVER_NAME,\n" +
            "       INITCAP(X.NTF_ACTION) AS APPROVER_ACTION,\n" +
            "       REPLACE(REPLACE(X.NTF_NOTE, CHR(13), ' '), CHR(10), '') AS NOTE,\n" +
            "       TO_CHAR(X.CREATION_DATE, 'DD-MON-RR HH24:MI') AS ACTION_DATE\n" +
            "FROM XXSSGIL.XXSSGIL_APPROVAL_HISTORYS_VV X,\n" +
            "     APPS.PER_ALL_PEOPLE_F PPF\n" +
            "WHERE X.EMPLOYEE_ID = PPF.PERSON_ID\n" +
            "  AND TRUNC(X.CREATION_DATE) BETWEEN PPF.EFFECTIVE_START_DATE \n" +
            "                                   AND PPF.EFFECTIVE_END_DATE  \n" +
            "  AND X.EXPENSE_REPORT_HDR_ID = :L_INVOICE_ID\n" +
            "ORDER BY X.SRL_NUM",nativeQuery = true)
    List<LeaveApproval> findLeaveApprovalRecord(@Param("L_INVOICE_ID") String headerId); //here L_INVOICE_ID is replace by report header id
}
