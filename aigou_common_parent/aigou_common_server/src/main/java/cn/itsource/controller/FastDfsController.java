package cn.itsource.controller;

import cn.itsource.util.AjaxResult;
import cn.itsource.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FastDfsController {

    /**
     * 文件上传
     * @return
     */
    @PostMapping("/fastdfs")
    public AjaxResult upload(MultipartFile file){
        String fileName = file.getOriginalFilename();
        //获取文件的扩展名
        fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            String upload = FastDfsApiOpr.upload(file.getBytes(), fileName);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("上传成功!").setObj(upload);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("上传失败!"+e.getMessage());
        }
    }

    /**
     * 文件删除
     * @return
     */
    @DeleteMapping("/fastdfs")
    public AjaxResult delete(String fileId){
        //    /group1/M00/00/01/rBAEtF1ESveAJIrtAATb0rfoOcM166.jpg
        try {
            fileId = fileId.substring(1);
            String groupName = fileId.substring(0,fileId.indexOf("/"));
            String fileName = fileId.substring(fileId.indexOf("/")+1);
            FastDfsApiOpr.delete(groupName,fileName);
            //成功
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除失败!"+e.getMessage());
        }

    }

}
