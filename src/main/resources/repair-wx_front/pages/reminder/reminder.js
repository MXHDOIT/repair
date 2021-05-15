// pages/reminder/reminder.js
const app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    array: [],
    nickName: ''
  },
  //催单请求
  reminder:function(e){
    console.log(e.currentTarget.dataset.text)
    //将值发送到服务器
    var that = this //创建一个名为that的变量来保存this当前的值  
    wx.request({
      url: app.data.url +'/user/reminders',
      method: 'post',
      data: {
        repairId: e.currentTarget.dataset.text
      },
      header: {
        'cookie': wx.getStorageSync("sessionid"),
        'content-type': 'application/x-www-form-urlencoded'  //这里注意POST请求content-type是小写，大写会报错  
      },
      success: function (res) {
        wx.showToast({
          title: '催单成功',
          icon: 'success',
          duration: 2000
        })
        console.log(res.data)

      },

    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    wx.getStorage({
      key: 'user',
      success(res) {
        that.setData({
          nickName: res.data.name
        })
      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var that = this
    console.log()
    wx.showLoading({
      title: '加载中',
    })
    // 将微信信息提交给服务器，并获取服务器传回的数据
    wx.request({
      url: app.data.url +'/wx/repair/reminder',
      method: 'post',
      data: {
      },
      header: {
        'cookie': wx.getStorageSync("sessionid"),
        'content-type': 'application/x-www-form-urlencoded'  //这里注意POST请求content-type是小写，大写会报错
      },

      success: function (res) {
        console.log(res);
       
        that.setData({
          array: res.data.data.repairs
        })
        wx.hideLoading()
      },

    });
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {


  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})