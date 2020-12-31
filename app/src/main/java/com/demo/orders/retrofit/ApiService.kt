package com.demo.orders.retrofit

import com.demo.orders.db.table.*
import com.demo.orders.db.table.response.LoginResponseModel
import com.demo.orders.helper.APIKey
import com.demo.orders.model.Login
import com.demo.orders.model.MobileModel
import com.demo.orders.util.Utils
import com.wizesales.visitormanagent.db.table.CompanyLoginResponse
import com.wizesales.visitormanagent.db.table.OderNewResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @POST(Utils.loginUrl)
    fun login(@Body login: Login): Call<LoginResponseModel>


    @POST(Utils.sendOTP)
    fun sendOTP(@Body login: Login): Call<StatusResponse>

    @POST(Utils.setmpin)
    fun setmpin(@Body login: Login): Call<LoginResponseModel>

    @GET(Utils.KEY_MENU)
    fun menu(): Call<MobileMenuResponse>


    //companyID
    @FormUrlEncoded
    @POST(Utils.checkCompnay)
    fun checkCompany(@Field(APIKey.company_id) companyID: String): Call<CompanyLoginResponse>

    @POST(Utils.inserCompanytUrl)
    fun createUser(@Body login: CompanyRegistration): Call<ResponseBody>

    //    Login
    @FormUrlEncoded
    @POST(Utils.validUserUrl)
    fun validUser(@Field(APIKey.KEY_CUSTOMER_ID) id: String, @Field(APIKey.company_id) companyID: String): Call<VerifyUserRsponse>

    @GET(Utils.KEY_ORDERS)
    fun getOrders(@Query(value = APIKey.KEY_ID) id: String): Call<ResponseOrdersDataSource>

    @GET(Utils.KEY_ORDERS_list)
    fun getOrdersList(@Query(value = APIKey.KEY_ID) id: String, @Query(value = APIKey.KEY_PAGE_COUNT) pageCount: Int): Call<ResponseOrdersDataSource>


    @GET(Utils.KEY_BANNER)
    fun getBanner(): Call<ResponseSliderItem>

    @GET(Utils.paymentTally)
    fun paymentTally(@Query(value = APIKey.KEY_ID) id: String): Call<StatusResponse>

    @POST(Utils.customersurvey_insert)
    fun customerSurveyInsert(@Body login: SurveyTable): Call<ResponseSurvey>

    @GET(Utils.get_customer_selected_surveylist)
    fun getSurvive(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String, @Query(value = APIKey.KEY_SURVEY_ID) surveyId: String): Call<ResponseSurvey>

    @GET(Utils.get_notificationcount)
    fun getNotificationcount(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String): Call<ResponseNotificationCount>

    @GET(Utils.get_notificationmessage)
    fun getNotificationmessage(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String, @Query(value = APIKey.KEY_PAGE_COUNT) pageCount: Int): Call<ResponseNotificationMessage>

    @POST(Utils.get_mobileTrackin)
    fun mobileModel(@Body login: MobileModel): Call<StatusResponse>

    @POST(Utils.logout)
    fun logout(@Body login: MobileModel): Call<StatusResponse>

    @GET(Utils.get_activesurveylist)
    fun getActiveSurvive(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String, @Query(value = APIKey.KEY_PAGE_COUNT) pageCount: Int): Call<ResponseSurveyList>

    @GET(Utils.get_completedsurvey_details)
    fun getCompletedSurvive(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String, @Query(value = APIKey.KEY_PRIME_ID) primeId: String): Call<ResponseSurveyCompleteList>

    @GET(Utils.get_scheme)
    fun getScheme(): Call<List<SchemeMasterTable>>

    @GET(Utils.get_customer_message)
    fun get_customer_message(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String, @Query(value = APIKey.KEY_PAGE_COUNT) pageCount: Int): Call<MessageResponse>

    @GET(Utils.get_customer_active_message)
    fun get_customer_active_message(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String): Call<MessageResponse>

    @GET(Utils.get_orders_by_id)
    fun get_orders_by_id(@Query(value = APIKey.KEY_ORDER_NO) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String): Call<OderNewResponse>

    @GET(Utils.customer_cutoff_time)
    fun checkCutOff(@Query(value = APIKey.KEY_COMPANY_CODE) id: String, @Query(value = APIKey.KEY_CUSTOMER_CODE) customer_code: String
                  ): Call<StatusResponse>

}
