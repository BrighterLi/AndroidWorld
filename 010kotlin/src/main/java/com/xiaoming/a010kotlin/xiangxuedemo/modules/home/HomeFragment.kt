package com.xiaoming.a010kotlin.xiangxuedemo.modules.home

import android.os.Bundle
import android.util.Log
import android.view.*
import com.xiaoming.a010kotlin.R
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.xiaoming.a010kotlin.xiangxuedemo.config.Flag
import com.xiaoming.a010kotlin.xiangxuedemo.data_model.request.NetworkResultData
import com.xiaoming.a010kotlin.xiangxuedemo.data_model.request.RequestAPI
import com.xiaoming.a010kotlin.xiangxuedemo.entity.HomeDataResponse
import com.xiaoming.a010kotlin.xiangxuedemo.modules.home.adapter.HomeInfoListAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.Response

//首页的Fragment
class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "首页", Toast.LENGTH_SHORT).show()
        val root: View? = inflater.inflate(R.layout.fragment_home, container, false)
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.personal_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requestHomeData()
    }
    /**
     * 请求首页的数据集
     */
    private fun requestHomeData() {
        RequestAPI.instanceRequestAction(Flag.SERVER_URL, "1", object : NetworkResultData() {

            // 失败 main 已经NetworkResultData切换过来了
            override fun requestError(info: String) {
                Log.e(Flag.TAG, "requestHomeData requestError info:$info")
                showResultError(info)
            }

            // 成功 main 已经NetworkResultData切换过来了
            override fun reqeustSuccess(result: Response) {
                // gons   json  ---> bean
                try {
                    val resultData = result.body()?.string().toString()
                    Log.e(Flag.TAG,
                            "成功  数据在response里面  获取后台给我们的JSON 字符串 resultData:$resultData")

                    // Gson解析成可操作的对象
                    val gson = Gson()
                    val homeDataResponse: HomeDataResponse = gson.fromJson(resultData, HomeDataResponse::class.java)
                    showResultSuccess(homeDataResponse)
                }catch (e: Exception) {
                    e.printStackTrace()

                    Log.e(Flag.TAG, "requestSuccess 解析数据时Exception:${e.message}")
                }
            }

        })
    }

    /**
     * TODO 首页的画面展示【成功】
     */
    private fun showResultSuccess(result: HomeDataResponse) {
        text_home.text = "欢迎同学们的到来>>>>>>>>>>>"

        // home_listview.adapter = HomeInfoListAdapter(context!!, result.data.news_list)
        home_listview.adapter = context?.myRun { HomeInfoListAdapter(it, result.data.news_list) }

        // 两者图片的显示
        Glide.with(iv_top).load(result.data.company_list[0].image).into(iv_top)
        Glide.with(iv_bottom).load(result.data.ad_list[0].image).into(iv_bottom)
    }

    /**
     * TODO 首页的画面展示【失败】
     */
    private fun showResultError(errorMsg: String) {
        Toast.makeText(activity, "首页数据请求失败: errorMsg:$errorMsg", Toast.LENGTH_SHORT).show()
    }
}

fun <T, R> T.myRun(m: (T) -> R) : R  = m(this)  // 调用高阶函数