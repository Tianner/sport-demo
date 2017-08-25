package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.common.fastDFS.FastDFSTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收上传文件action类
 * Created by Administrator on 2017/8/16.
 */
@Controller
public class UploadAction {

    /**
     * 接受上传文件通用方法，上传后存储到fastDFS
     * @param file
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadFile.do")
    @ResponseBody
    public Map<String,String> uploadFile(@RequestParam("file")MultipartFile file, Model model){
        Map<String ,String> map = new HashMap<>();
        if(file!=null){
            String imgUrl;
            try {
                //file.transferTo(new File(imgUrl));
                imgUrl = FastDFSTools.uploadFile(file.getBytes(),file.getOriginalFilename());
                map.put("imgUrl",imgUrl);
                map.put("ipRoot",FastDFSTools.ROOT_PATH);
            } catch (Exception e ){
                map.put("imgUrl","");
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 接受批量上传文件通用方法，上传后存储到fastDFS
     * @param files
     * @return
     */
    @RequestMapping(value = "/uploadPics.do")
    @ResponseBody
    public List<String> uploadPics(@RequestParam MultipartFile[] files){
        List<String> list = new ArrayList<>();
        if(files!=null&&files.length>0){
            String imgUrl;
            for (MultipartFile file:files) {
                try {
                    imgUrl = FastDFSTools.uploadFile(file.getBytes(),file.getOriginalFilename());
                    System.out.println(imgUrl+"-------");
                    list.add(FastDFSTools.ROOT_PATH+imgUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    @RequestMapping("/uploadFck.do")
    @ResponseBody
    public Map<String,Object> uploadFile(@RequestParam MultipartFile[] uploadFile){
        Map<String,Object> map = new HashMap<>();
        if(uploadFile!=null){
            String imgUrl = "";
            for (MultipartFile file:uploadFile) {
                try {
                    imgUrl = FastDFSTools.uploadFile(file.getBytes(), file.getOriginalFilename());
                    map.put("error",0);
                    map.put("url",FastDFSTools.ROOT_PATH+imgUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
