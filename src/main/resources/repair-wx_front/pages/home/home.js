var app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    name:'',
    gender:'',
    account:'',
    password:''
  },
  
  //联系电话
  tel() {
    wx.makePhoneCall({
      phoneNumber: '13186379659'
    })
  },
  //详细资料
  content() {
    wx.navigateTo({
      url: '/pages/me/me'
    })
  },
  //报修记录
  all() {
    wx.navigateTo({
      url: '/pages/all/all'
    })
  },
  //催单
  reminder() {
    wx.navigateTo({
      url: '/pages/reminder/reminder'
    })
  },
  //维修状态
  loading() {
    wx.navigateTo({
      url: '/pages/loading/loading'
    })
  },
  //已完成的报修
  task() {
    wx.navigateTo({
      url: '/pages/task/task'
    })
  },
  // 获取输入账号
  accountInput:function(e) {
    this.setData({
      account: e.detail.value
    })
  },
  // 获取输入密码
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
  //登录请求
  login: function(e) {
    var that = this;
    if (this.data.account.length == 0 || this.data.password.length == 0) {
      wx.showToast({
        title: '账号或密码不能为空',
        icon: 'none',
        duration: 2000
      })
    }else{
      wx.request({
        url: 'http://localhost:8888/wx/login',
        method:'post',
        data:{
          account:that.data.account,
          password:that.data.password
        },
        header:{
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        success(res) {
          console.log(res)
          if (res.data.success) {
            that.setData({
              name:res.data.data.user.name,
              gender:res.data.data.user.sexual
            }),
            wx.removeStorageSync('user')
            wx.setStorageSync('user', res.data.data.user)
            wx.removeStorageSync('sessionid')
            wx.setStorageSync('sessionid', res.header["Set-Cookie"])
          } else {
            wx.showToast({
              title: res.data.message,
              icon: 'none',
              duration: 2000
            })
          }
        }
      })
    }
  },
  //退出
  signOut:function(e){
    console.log("sign out");
    wx.removeStorageSync('user')
    wx.removeStorageSync('sessionid')
    wx.switchTab({
      url: '/pages/index/index',
    })

    var that = this;
    that.setDatasetData({
      name:'',
      gender:''
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    var user = wx.getStorageSync('user');
    console.log(user);
    if(user){
      that.setData({
        name:user.name,
        gender:user.sexual
      })
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})