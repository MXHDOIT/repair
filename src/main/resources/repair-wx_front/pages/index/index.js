//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    test:"",
    img:"",
    image_name:'',
    imgUrls: [
      '/images/schoolGate.jpg',
      '/images/square.jpg',
      '/images/library.jpg',
    ],
    image_url:"",
    array: [],
    cate_name:[],
    nickName:'',
    index:0
  },
  onLoad: function () {
  
  },
  onShow(){
    var that =this
    wx.getStorage({
      key: 'user',
      success(res) {
        console.log(res)
        that.setData({
          nickName: res.data.name
        })
        console.log(that.data.name)
      }
    })
  }
,
  bindPickerChange(e) {
    this.setData({
      index: e.detail.value
    })
  },
  bindMultiPickerChange(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      multiIndex: e.detail.value
    })
  },
  uploadimg: function () {
    var that = this;
    wx.chooseImage({  //从本地相册选择图片或

      success: function (res) {
        that.setData({
          img: res.tempFilePaths
        })

        //  返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        wx.uploadFile({
         
          url: app.data.url +'/wx/picture/upload',
          filePath: tempFilePaths[0],
          name: 'file',
          success: function (res) {
            console.log(res)
            console.log(JSON.parse(res.data).data.pictureUrl)
            //打印
            that.setData({
              image_url: JSON.parse(res.data).data.pictureUrl
            }) 

            console.log("result : "+that.data.image_url);
          }
        })

      }
    })
  },

  formSubmit(e){
    // 获取form表单值
    var arr = [];
    wx.showLoading({
      title: '提交中',
    })
    arr['place'] = e.detail.value.place
    arr['detail'] = e.detail.value.content
    arr['imageUrl'] = this.data.image_url
    console.log(arr)
    //将值发送到服务器
    var that = this //创建一个名为that的变量来保存this当前的值  
    wx.request({
      url: app.data.url +'/wx/repair/add',
      method: 'post',
      data: arr,
      header: {
        'cookie': wx.getStorageSync("sessionid"),
        'content-type': 'application/x-www-form-urlencoded'  //这里注意POST请求content-type是小写，大写会报错  
      },
      success: function (res) {
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        })
        console.log(res.data)
        
      },
      
    });

  },
   onShareAppMessage(res) {
    
  }
})
