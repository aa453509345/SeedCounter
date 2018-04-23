<template>
    <list style="flex-direction: column; width: 750;top: 0" ref="list" showRefresh="true" @refresh="onrefresh">
        <cell>
            <!-- <text>这是一个新的路由页面1</text>
            <image v-for="(imgUrl, index) in imgUrls" :key="index" @click="preview(index)" style="width: 500px; height: 500px;" :src="imgUrl" resize="contain"></image>
            <WxcButton text="修改 mediator-store" @wxcButtonClicked="changeStore"></WxcButton>
            <text>{{store}}</text> 
            <text>{{contacts}}</text> -->
            <text @click="picktest" style="font-size: 40;flex:1">pick</text>
            <text @click="takePic">takePic</text>
        </cell>
    </list>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config')
var bmImage = weex.requireModule('bmImage')
var bmRecognize = weex.requireModule('bmRecognize')
import { WxcButton } from 'weex-ui';
export default {
    created () {
        this.bindEvent()
    },
    components: {
        WxcButton
    },
    data () {
        return {
            store: null,
            imgUrls: [],
            contacts: null
        }
    },
    methods: {
        bindEvent () {
            this.$event.on('store.count.get', data => {
                this.store = data
            })
            this.$event.on('store.change', data => {
                this.store = data
            })
        },
        changeStore () {
            // var self = this
            // this.$event.emit('store.count.add')
            // this.$storage.setSync('name',222)
            // var resData = this.$storage.getSync('name')
            // var test = this.$storage.getSync("name")
            this.$image.pick({
                maxCount: 9,
                imageWidth: '800',
                allowCrop: true
            }).then(imgUrls => {
                this.imgUrls = imgUrls
            }, error => {
                console.log(error)
            })
            // this.$coms.contacts().then(data => {
            //     this.contacts = data
            // })
        },
        pick () {
            bmImage.pick({
                maxCount: 9,
                imageWidth: 1000
            }, data => {
                this.imgUrl = data.data[0]
            })
        },
        preview (index) {
            this.$image.preview({
                index,
                images: this.imgUrls
            })
        },
        onrefresh () {
            this.$geo.get().then(resData => {
                setTimeout(() => {
                    this.$refs['list'].refreshEnd()
                }, 1000)
            })
        },
        picktest(){
               this.$image.pick({
                maxCount: 1,
                imageWidth: '800',
            }).then(imgUrls => {
                setTimeout(()=>{
 bmRecognize.recognize(imgUrls[0],(data)=>{
   console.log(data)
 })
                },100)
           
            }, error => {
            })
        },
        takePic(){
    this.$image.camera({
    imageWidth: '800',                  
    allowCrop: false                  
})
.then(resData => {
console.log(resData)
bmRecognize.recognize(resData[0],()=>{

})
}, error => {
    console.log(error)
})
        }
    }
}
</script>
