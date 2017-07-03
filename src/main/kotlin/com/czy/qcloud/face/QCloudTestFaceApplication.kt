package com.czy.qcloud.face

import com.qcloud.image.ImageClient
import com.qcloud.image.common_utils.CommonFileUtils
import com.qcloud.image.demo.Demo
import com.qcloud.image.request.IdcardDetectRequest
import com.qcloud.image.request.PornDetectRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import com.qcloud.image.request.FaceCompareRequest




@SpringBootApplication
class QCloudTestFaceApplication {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(QCloudTestFaceApplication.javaClass)
        private val appid = 1253953328
        private val SecretID = "AKIDoW4QcbT0PcecUBeSAg083pOkurtmIhaI"
        private val secretKey = "jGbkhJp8JI4e4ISgvhDkIjy1vd8QXR4l"
        private val bucketName = "test"
        private val imageClient = ImageClient(appid, SecretID, secretKey)

    }

    fun go() {
        val pornUrlList = arrayOfNulls<String>(3)
        pornUrlList[0] = "http://hearthstone.nos.netease.com/1/artworkGvG/GoblinBlastmagel.jpg"
        pornUrlList[1] = "http://hearthstone.nos.netease.com/1/artworknaxx/Faerlinal.jpg"
        pornUrlList[2] = "http://hearthstone.nos.netease.com/1/artworknaxx/KelThuzadl.jpg"
        val pornReq = PornDetectRequest(bucketName, pornUrlList)

        val pornDetect = imageClient.pornDetect(pornReq)
        System.out.println("porn detect ret:" + pornDetect)
    }

    fun go1() {
        val idcardNameList = arrayOfNulls<String>(1)
        val idcardImageList = arrayOfNulls<String>(1)
        //识别身份证正面
        try {
            idcardNameList[0] = "before.png"
            idcardImageList[0] = CommonFileUtils.getFileContent("F:\\Comup\\Desktop\\before.png")
        } catch (ex: Exception) {
            logger.info("", ex)
        }


        var idReq = IdcardDetectRequest(bucketName, idcardNameList, idcardImageList, 0)
        var ret = imageClient.idcardDetect(idReq)
        System.out.println("idcard detect ret:" + ret)

        //识别身份证反面
//        try {
//            idcardNameList[0] = "back.png"
//            idcardImageList[0] = CommonFileUtils.getFileContent("F:\\Comup\\Desktop\\back.png")
//        } catch (ex: Exception) {
//            logger.info("",ex)
//        }
//
//
//        var idReq = IdcardDetectRequest(bucketName, idcardNameList, idcardImageList, 1)
//        var ret = imageClient.idcardDetect(idReq)
//        System.out.println("idcard detect ret:" + ret)
    }

    fun go2(){
        //2. 图片内容方式
        val compareNameList = arrayOfNulls<String>(2)
        val compareImageList = arrayOfNulls<String>(2)
        try {
            compareNameList[0] = "t1.jpg"
            compareNameList[1] = "t2.jpg"
            compareImageList[0] = CommonFileUtils.getFileContent("F:\\Comup\\Desktop\\t1.jpg")
            compareImageList[1] = CommonFileUtils.getFileContent("F:\\Comup\\Desktop\\t2.jpg")
        } catch (ex: Exception) {
           logger.info("",ex)
        }

        val faceCompareReq = FaceCompareRequest(bucketName, compareNameList, compareImageList)
        val ret = imageClient.faceCompare(faceCompareReq)
        System.out.println("face compare ret:" + ret)
    }
}

fun main(args: Array<String>) {
    QCloudTestFaceApplication().go2()
    //SpringApplication.run(QCloudTestFaceApplication::class.java, *args)
}
