package com.zrkj.mapper;

import com.zrkj.pojo.Qr;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/20.
 */
public interface QrMapper {
    @Insert("<script>" +
            "Insert into tb_qr" +
            "(storeId)" +
            "values" +
            "<foreach collection=\"list\" item = \"item\" separator = \",\">" +
            "(#{item.storeId})" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true)
    int doCreateBatch(List<Qr> list);

    @Update("<script> " +
            "update tb_qr\n" +
            "SET\n" +
            "file_url =\n" +
            "<foreach collection=\"list\" item=\"item\" separator=\" \" open=\"case id\" close=\"end\">\n" +
            "        WHEN #{item.id} THEN #{item.file_url}\n" +
            "</foreach>\n" +
            ",url =\n" +
            "<foreach collection=\"list\" item=\"item\" separator=\" \" open=\"case id\" close=\"end\">\n" +
            "        WHEN #{item.id} THEN #{item.url}\n" +
            "</foreach>\n" +
            "WHERE id in\n" +
            "<foreach collection=\"list\" item=\"item\" open=\"(\" close=\")\" separator=\",\">\n" +
            "        #{item.id}\n" +
            "</foreach>" +
            "</script>")
    int doUpdateBatch(List<Qr> list);

    @Update("update tb_qr set isFailure = 1 where id = #{id}")
    int doUpdate(Integer id);

    @Select("<script>select * from tb_qr where storeId = #{storeId}<if test=\"isF!=null\" >and isFailure=#{isF} </if></script>")
    List<Qr> getQrList(@Param("storeId") Integer storeId,@Param("isF") Byte isF);

    @Select("select * from tb_qr where id = #{id}")
    Qr getQrByID(Integer id);

    @Select("select * from tb_qr where storeId = #{storeId} and  isFailure= 0 limit #{start},#{end}")
    List<Qr> getQrListByNum(@Param("start") Integer start, @Param("end") Integer end, @Param("storeId") Integer storeId);
}
