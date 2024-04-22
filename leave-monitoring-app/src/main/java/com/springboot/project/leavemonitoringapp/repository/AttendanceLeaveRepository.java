package com.springboot.project.leavemonitoringapp.repository;

import com.springboot.project.leavemonitoringapp.model.AttendanceLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceLeaveRepository extends JpaRepository<AttendanceLeave,Long> {
    @Query(value = "SELECT\n" +
            "        *\n" +
            "FROM\n" +
            "  (  select \n" +
            "    hdr.report_header_id,\n" +
            "    entry_date,\n" +
            "    status_flg,\n" +
            "    WF_ITEM_KEY,\n" +
            "    hdr.creation_date,\n" +
            "    employee_number emp_id,\n" +
            "    leave_start_date,\n" +
            "    leave_end_date,\n" +
            "    leave_type\n" +
            "from \n" +
            "    APPS.XXSSGIL_APPROVAL_LV_HDR HDR,\n" +
            "    APPS.XXSSGIL_APPROVAL_LV_LINE LINE\n" +
            "where \n" +
            "    HDR.REPORT_HEADER_ID = LINE.REPORT_HEADER_ID) leave,\n" +
            "(SELECT \n" +
            "ROWNUM SRL_NUM,\n" +
            "TRUNC (\n" +
            "          ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60 / (24 * 60 * 60))\n" +
            "       || ':'\n" +
            "       || TRUNC (\n" +
            "             MOD ( ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60,\n" +
            "                  (24 * 60 * 60))\n" +
            "             / (60 * 60))\n" +
            "       || ':'\n" +
            "       || TRUNC (\n" +
            "             MOD ( ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60, (60 * 60))\n" +
            "             / 60)\n" +
            "       || ':'\n" +
            "       || TRUNC (MOD ( ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60, 60))\n" +
            "          W_HOUR,\n" +
            "       TO_CHAR (ACT_IN_TIME, 'HH:MI:SS AM ') ACT_IN_TIME,\n" +
            "       TO_CHAR (ACT_OUT_TIME, 'HH:MI:SS AM ') ACT_OUT_TIME,\n" +
            "       --EMP_NAME,\n" +
            "       EMPLOYEE_NUMBER,\n" +
            "       EMPLOYMENT_CATEGORY,\n" +
            "       DEPT,\n" +
            "       GROUP_NAME,\n" +
            "       WORKING_DATE,\n" +
            "       WORKING_DAY,\n" +
            "       CASE WHEN STATUS='P' THEN 'Present' WHEN STATUS ='H' THEN 'Holiday' WHEN STATUS='LV' THEN 'Leave' WHEN STATUS='A' THEN 'Absent' WHEN STATUS='L' THEN 'Late' \n" +
            "       WHEN STATUS='O' THEN 'Offday' ELSE NULL END AS STATUS,\n" +
            "       Leave_Type action_status\n" +
            "--       LOCATION_CODE,\n" +
            "--       PAYROLL_NAME,\n" +
            "--      VAL\n" +
            "  FROM (  SELECT DISTINCT\n" +
            "                 TO_DATE (ACT_IN_TIME, 'DD-MON-YYYY HH24:MI:SS ') ACT_IN_TIME,\n" +
            "                 TO_DATE (ACT_OUT_TIME, 'DD-MON-YYYY HH24:MI:SS ') ACT_OUT_TIME,\n" +
            "                 (   PAPF.FIRST_NAME\n" +
            "                  || ' '\n" +
            "                  || PAPF.MIDDLE_NAMES\n" +
            "                  || ' '\n" +
            "                  || PAPF.LAST_NAME)\n" +
            "                    AS EMP_NAME,\n" +
            "                 PAPF.EMPLOYEE_NUMBER,\n" +
            "                 FLV.MEANING EMPLOYMENT_CATEGORY,\n" +
            "                 HAOU.NAME DEPT,\n" +
            "                 --  pj.NAME,\n" +
            "                 PPG.GROUP_NAME,\n" +
            "                 XESD.WORKING_DATE,\n" +
            "                 TO_CHAR (XESD.WORKING_DATE, 'Day') WORKING_DAY,\n" +
            "                 XESD.STATUS,\n" +
            "                 paat.name Leave_Type,\n" +
            "                 HL.LOCATION_CODE,\n" +
            "                 PAYROLL.PAYROLL_NAME,\n" +
            "                 DECODE (FLV2.MEANING,\n" +
            "                         'Sales Field Officer', 'Sales',\n" +
            "                         'Hospital Employee', 'Hospital',\n" +
            "                         NULL)\n" +
            "                    VAL\n" +
            "            FROM XXSSGIL.XXSSGIL_EMP_SHIFT_PATT_ALLOC XESP,\n" +
            "                 XXSSGIL.XXSSGIL_SHIFT_PATTERN_DEFN XSPD,\n" +
            "                 XXSSGIL.XXSSGIL_EMP_SHIFT_ALLOC_DET XESD,\n" +
            "                 APPS.PER_ALL_PEOPLE_F PAPF,\n" +
            "                 --per_jobs pj,\n" +
            "                 APPS.PER_ALL_ASSIGNMENTS_F PAAF,\n" +
            "                 APPS.HR_LOCATIONS HL,\n" +
            "                 APPLSYS.FND_LOOKUP_VALUES FLV,\n" +
            "                 APPS.HR_ALL_ORGANIZATION_UNITS HAOU,\n" +
            "                 APPS.PAY_PEOPLE_GROUPS PPG,\n" +
            "                 APPS.PAY_ALL_PAYROLLS_F PAYROLL,\n" +
            "                 APPS.PER_PAY_BASES PPB,\n" +
            "                 APPS.PER_ABSENCE_ATTENDANCES PAT,\n" +
            "                 APPS.PER_ABSENCE_ATTENDANCE_TYPES PAAT,\n" +
            "                 APPLSYS.FND_LOOKUP_VALUES FLV2\n" +
            "           --   apps.per_abs_attendance_types_v attv,\n" +
            "           -- apps.per_absence_attendances paa\n" +
            "           WHERE PAPF.PERSON_ID = XESP.PERSON_ID\n" +
            "                 AND XESP.CREATION_DATE BETWEEN PAPF.EFFECTIVE_START_DATE\n" +
            "                                            AND PAPF.EFFECTIVE_END_DATE\n" +
            "                 AND PAAF.PERSON_ID = PAPF.PERSON_ID\n" +
            "                 --AND paaf.job_id = pj.job_id\n" +
            "                 --AND XESP.CREATION_DATE BETWEEN PAAF.EFFECTIVE_START_DATE AND PAAF.EFFECTIVE_END_DATE\n" +
            "                 AND HL.LOCATION_ID(+) = PAAF.LOCATION_ID\n" +
            "                 AND FLV.LOOKUP_CODE(+) = PPB.NAME\n" +
            "                 AND UPPER (FLV.LOOKUP_TYPE(+)) =\n" +
            "                        UPPER ('SSGIL_BD_EMPL_CAT_SALARY_BASIS')\n" +
            "                 AND FLV.ENABLED_FLAG(+) = 'Y'\n" +
            "                 AND HAOU.ORGANIZATION_ID = PAAF.ORGANIZATION_ID\n" +
            "                 AND PPG.PEOPLE_GROUP_ID(+) = PAAF.PEOPLE_GROUP_ID\n" +
            "                 AND PPG.ENABLED_FLAG(+) = 'Y'\n" +
            "                 AND PPG.END_DATE_ACTIVE(+) IS NULL\n" +
            "                 AND XSPD.PATTERN_NUMBER = XESP.SHIFT_PATTERN\n" +
            "                 AND XESD.PATT_ALLOC_ID = XESP.PATT_ALLOC_ID\n" +
            "                 AND PAAF.CREATION_DATE BETWEEN PAYROLL.EFFECTIVE_START_DATE(+)\n" +
            "                                            AND PAYROLL.EFFECTIVE_END_DATE(+)\n" +
            "                 AND PAYROLL.PAYROLL_ID(+) = PAAF.PAYROLL_ID\n" +
            "                 --- join for getting leave name ----\n" +
            "                 AND XESD.WORKING_DATE BETWEEN XESP.START_DATE\n" +
            "                                           AND XESP.END_DATE\n" +
            "                 AND PAAF.PAY_BASIS_ID = PPB.PAY_BASIS_ID\n" +
            "                 AND XESD.PERSON_ID = PAT.PERSON_ID(+)\n" +
            "                 --AND paaf.business_group_id = pj.business_group_id\n" +
            "                 --and paa.absence_attendance_type_id = attv.absence_attendance_type_id\n" +
            "                 --and PAPF.person_id=paa.person_id\n" +
            "                 AND XESD.WORKING_DATE BETWEEN PAT.DATE_START(+)\n" +
            "                                           AND PAT.DATE_END(+)\n" +
            "                 AND PAT.ABSENCE_ATTENDANCE_TYPE_ID =\n" +
            "                        PAAT.ABSENCE_ATTENDANCE_TYPE_ID(+)\n" +
            "                 AND PAAT.DATE_END(+) IS NULL\n" +
            "                 --- join for emp category--------\n" +
            "                 AND FLV2.LOOKUP_TYPE(+) = 'EMPLOYEE_CATG'\n" +
            "                 AND FLV2.ENABLED_FLAG(+) = 'Y'\n" +
            "                 AND FLV2.END_DATE_ACTIVE(+) IS NULL\n" +
            "                 -- correction over version 1.1 of attendance update logic to include hospital--\n" +
            "                 -- add in condition for cases 5,6,7 to include hospital category------\n" +
            "                 AND FLV2.MEANING(+) IN\n" +
            "                        ('Sales Field Officer', 'Hospital Employee')\n" +
            "                 AND PAAF.EMPLOYEE_CATEGORY = FLV2.LOOKUP_CODE(+)\n" +
            "                 /*Commented due to assignment changes in between Employee wise Pattern Allocation on 25-May-2016*/\n" +
            "                 AND XESD.WORKING_DATE BETWEEN PAAF.EFFECTIVE_START_DATE\n" +
            "                                           AND PAAF.EFFECTIVE_END_DATE\n" +
            "                 AND PAAF.PERSON_ID = XESD.PERSON_ID\n" +
            "--               AND TRIM (XESD.WORKING_DATE) BETWEEN TO_DATE ('$from_date', 'YYYY-MM-DD')\n" +
            "--                             AND TO_DATE ('$to_date', 'YYYY-MM-DD')\n" +
            "                 AND TRUNC (XESD.WORKING_DATE) BETWEEN '26-Mar-2024'--:P_DATE_FROM \n" +
            "                 AND  '25-Apr-2024'--:P_DATE_TO\n" +
            "--                 AND PAPF.EMPLOYEE_NUMBER = :P_EMPLOYEE_ID\n" +
            "        --(select user_name from fnd_user where user_id =:P_user_id)\n" +
            "            AND \n" +
            "                 CASE WHEN STATUS='P' THEN 'Present' WHEN STATUS ='H' THEN 'Holiday' WHEN STATUS='LV' THEN 'Leave' WHEN STATUS='A' THEN 'Absent' WHEN STATUS='L' THEN 'Late' \n" +
            "       WHEN STATUS='O' THEN 'Offday' ELSE NULL END=NVL(:P_STATUS,CASE WHEN STATUS='P' THEN 'Present' WHEN STATUS ='H' THEN 'Holiday' WHEN STATUS='LV' THEN 'Leave' WHEN STATUS='A' THEN 'Absent' WHEN STATUS='L' THEN 'Late' \n" +
            "       WHEN STATUS='O' THEN 'Offday' ELSE NULL END)\n" +
            "        ORDER BY XESD.WORKING_DATE, PAPF.EMPLOYEE_NUMBER ASC)) Attend\n" +
            "where\n" +
            "Attend.EMPLOYEE_NUMBER = leave.emp_id \n" +
            "and Attend.status in ('Absent','Late')  \n" +
            "and leave.STATUS_FLG <>  'Rejected'\n" +
            "--and leave.STATUS_FLG  in ( 'Approved')\n" +
            "--and leave.report_header_id = '13789'\n" +
            "--and Attend.leave_type is null\n" +
            "and TO_DATE(Attend.WORKING_DATE, 'DD-Mon-YY') BETWEEN TO_DATE(leave.leave_start_date, 'DD-Mon-YY HH:MI:SS AM') AND TO_DATE(leave.leave_end_date, 'DD-Mon-YY HH:MI:SS AM')\n" +
            "order by leave.entry_date asc", nativeQuery = true)
    List<AttendanceLeave> findAttendanceLeaveRecord(@Param("P_STATUS") String status);
}
