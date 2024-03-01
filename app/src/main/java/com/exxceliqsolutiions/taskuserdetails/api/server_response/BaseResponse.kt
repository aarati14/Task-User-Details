package com.exxceliqsolutiions.taskuserdetails.api.server_response

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/*
*  This is the abstract class created to get base parameters received through API.
* */


abstract class BaseResponse
    (
    @SerializedName("page")
    open var pageNumber: String,
    @SerializedName("per_page")
    open var perPageRecords: String,
    @SerializedName("total")
    open var totalRecords: String,
    @SerializedName("total_pages")
    open var totalPages: String,
    @SerializedName("support")
    open var supportDetails : JsonObject
            )
