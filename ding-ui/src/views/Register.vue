/** 2022/4/24
* 作者:丁557
* 功能:注册界面
*/
<template>
<div style="width: 100%;height: 100vh; overflow: hidden;background-size: 100% 100%;" :style ="note" >
  <div style="width: 300px; margin: 150px auto">
    <div style="color: #CCCCCC; font-size: 30px;text-align: center; padding: 30px 0">欢迎注册</div>
    <el-form ref="form"  :model ="form" size ="normal">
     <el-form-item >
       <el-input   placeholder="请输入用户名" v-model="form.name" :prefix-icon="User"></el-input>
     </el-form-item>
      <el-form-item >
        <el-input  placeholder="请输入密码" v-model="form.password"  :prefix-icon="Lock" show-password></el-input>
      </el-form-item>
     <el-form-item >
       <el-input  placeholder="请再次输入密码" v-model="form.confirm" :prefix-icon="Lock" show-password></el-input>
     </el-form-item>
      <el-form-item >
        <el-input  placeholder="请输入手机号" v-model="form.phone" :prefix-icon="Iphone" show-password></el-input>
      </el-form-item>
      <el-form-item  >

        <el-input
            v-model="form.verification"
            placeholder="请输入验证码"
            style="width: 185px; "
            oninput="value=value.replace(/[^\d]/g,'')"
            clearable
        /> &nbsp;&nbsp;&nbsp;
        <el-button class="lt-but" :disabled="ioncode" @click="get_verification">{{ Obtain=="" ? "获取验证码" : `${Obtain} 秒后在试` }} </el-button>
      </el-form-item>
      <el-button style="width: 100% " type="primary" @click="register">注册</el-button>


    </el-form>
  </div>
</div>
</template>

<script>
import { Bowl,UserFilled,User,Lock,Iphone } from '@element-plus/icons'
import request from "@/utils/request";

export default {

  name: "Login",
  setup() {
    return {
      Bowl,UserFilled,User,Lock,Iphone
    }
  },
  data() {
    return {
      note: {
        backgroundImage: "url(" + require("./../assets/pic/img.png") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: 100
      },
      Obtain: '', // 获取验证码倒计时
      timer: null,
      ioncode: false, // 禁用倒计时按妞
      formData: {
        phone: '',
        verification: '',
      },

    form: {}
    }
  },
  methods: {
    register(){
      if (this.form.password !== this.form.confirm){
        this.$message({
          type: "error",
          message: "两次密码不一致"
        })
        return
      }
      request.post("/user/register",this.form).then(res => {
      if(res.code === '0'){
        this.$message({
          type:"success",
          message:"注册成功"
        })
        this.$router.push("/")  //成功后进行页面跳转到主页
      }else {
        this.$message({
          type:"error",
          message:res.msg
        })
      }
      })
    },
    //校验手机号及倒计时60秒
    get_verification() {
      const verify = /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1|8|9]))\d{8}$/
      if (this.form.phone === '') {
        this.$message('请输入联系电话')
      } else {
        if (verify.test(this.form.phone)) {
          request.get("/user/Sms", {
            params: {
              phone: this.form.phone
            }
          }).then(res =>{
            console.log(res);
            if(res.code === '0'){
              this.$message({
                type:"success",
                message:"发送成功。有效期5分钟"
              })
            }else {
              this.$message({
                type:"error",
                message:res.msg
              })
            }
          })
          const TIME_COUNT = 60
          if (!this.timer) {
            this.Obtain = TIME_COUNT
            this.ioncode = true
            this.timer = setInterval(() => {
              if (this.Obtain > 0 && this.Obtain <= TIME_COUNT) {
                this.Obtain--
              } else {
                this.ioncode = false
                clearInterval(this.timer)
                this.timer = null
              }
            }, 1000)
          }
        } else {
          this.$message({
            message: '请输入正确的联系电话',
            type: 'warning'
          })
        }
      }
    }
  }

}
</script>

<style scoped>

</style>