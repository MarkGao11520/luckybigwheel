package com.zrkj.tools;

import com.zrkj.pojo.Store;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/4/25.
 */
public class Tools {

    /**
     *
     * @param request   获取地址用的
     * @param uploadPath   具体路径
     * @param multipartFile   上传的文件
     * @return
     */
    public static Map<String,String> uploadFile(HttpServletRequest request, String uploadPath, MultipartFile multipartFile){
        Map<String,String> map = new HashMap<String,String>();
        try {
            UUID uuid = UUID.randomUUID();
            String path = request.getServletContext().getRealPath(
                    "/");
            String userPath = "/" + uploadPath+"/";
            String realPath = path + userPath ;
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            System.out.println(multipartFile.getOriginalFilename());
            System.out.println(multipartFile.getOriginalFilename().lastIndexOf("."));
            String filename = uuid.toString()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            System.out.println(filename);
            String fileurl = realPath+filename;
            multipartFile.transferTo(new File(fileurl));
            map.put("isSuccess","true");
            map.put("url",userPath.substring(1,userPath.length())+filename);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("isSuccess","false");
        map.put("url","");
        return map;
    }



    public static String dataLongToString(long time){
        Date dt=new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(dt);
    }

    public static Long dataStringToLong(String time) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt=sdf.parse(time);
            return dt.getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Long obtianTodayTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(date)+" 00:00:00";
        try {
            Date date1 = sdf1.parse(s);
            return date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Store obtainPrincipal(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Store store = new Store();
        if (principal instanceof UserDetails) {
            store = (Store) principal;
        }
        return store;
    }
}
