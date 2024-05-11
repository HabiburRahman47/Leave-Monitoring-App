package com.springboot.project.leavemonitoringapp.repository;

import com.springboot.project.leavemonitoringapp.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeaveIssueRepository extends JpaRepository<Leave, Long> {
    @Query(value = "select \n" +
            "  HDR.REPORT_HEADER_ID,\n" +
            "  APPLICATION_TYPE,\n" +
            "LEAVE_TYPE,\n"+
            "  ENTRY_DATE,\n" +
            "  STATUS_FLG\n" +
            "from \n" +
            "    APPS.XXSSGIL_APPROVAL_LV_HDR HDR,\n" +
            "    APPS.XXSSGIL_APPROVAL_LV_LINE LINE\n" +
            "where \n" +
            "    HDR.REPORT_HEADER_ID = LINE.REPORT_HEADER_ID \n" +
            "and HDR.REPORT_HEADER_ID = :header_id", nativeQuery = true)
    List<Leave> findLeaveIssue(@Param("header_id") String headerId);
}
