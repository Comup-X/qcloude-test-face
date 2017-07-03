package com.czy.qcloud.face

/**
 * Created by Comup on 2017/7/3.
 */
data class CreatePersonRequest(
        //项目ID 必须
        var appid: String?,
        //空间名称 必须
        var bucket: String?,
        //必须    Array(String)    加入到组的列表
        var group_ids: Array<String>?,
        // 必须    String    指定的个体id
        var person_id: String?,
        //可选    Binary    图片内容
        var image: ByteArray?,
        //   可选    String    图片的url, image和url只提供一个即可, 如果都提供, 只使用url
        var url: String?,
        //可选    String    名字
        var person_name: String?,
        //可选    String    备注信息
        var tag: String?
)