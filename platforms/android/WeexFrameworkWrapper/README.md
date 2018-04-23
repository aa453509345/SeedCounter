### bmRecognize 粒子计数module
#### 引用方式 
`
var bmRecognize = weex.requireModule('bmRecognize')
`
#### 暴露方法 
`
recognize(String path,JSCallback callback)
`
#### 调用方式
`
  bmRecognize.recognize('pic path',(resData)=>{              
  })
`
#### 返回体resData结构 
`{
 	errorMsg:'',
 	status:0,
 	data:{
 	   number:100
 	}
 }`
 
#### status说明
    0     识别成功
    1000  无效的路径
    1001  识别库错误  
    1002  位置错误
    
    
#### 备注
1. recognize方法中第一个参数是选取图片的本地路径 可通过eros框架方法pick()和camera()获得，详见Eros文档
  [DOC](https://bmfe.github.io/eros-docs/#/)
