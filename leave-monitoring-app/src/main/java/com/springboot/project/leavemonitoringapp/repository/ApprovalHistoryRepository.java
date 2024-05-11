package com.springboot.project.leavemonitoringapp.repository;

import com.springboot.project.leavemonitoringapp.model.ApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprovalHistoryRepository extends JpaRepository<ApprovalHistory,Integer> {
    @Query(value = "select\n" +
            "                         SRL_NUM,\n" +
            "                          EXPENSE_REPORT_HDR_ID,\n" +
            "                          NTF_RESPONDER,\n" +
            "                          NTF_ACTION,\n" +
            "                          NTF_NOTE\n" +
            "                          from \n" +
            "                          XXSSGIL.XXSSGIL_APPROVAL_HISTORYS_VV \n" +
            "                          where EXPENSE_REPORT_HDR_ID= :header_id",nativeQuery = true)
    List<ApprovalHistory> findApprovalHistory(@Param("header_id") String header_id);
}
