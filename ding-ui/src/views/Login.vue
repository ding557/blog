/** 2022/4/24
* 作者:丁557
* 功能:登录界面
*/
<template>
   <transition>
<div style="width: 100%;height: 100vh; overflow: hidden;background-size: 100% 100%;" :style ="note">
  <div style="width: 400px; margin: 150px auto">
    <div style="color: #CCCCCC; font-size: 30px;text-align: center; padding: 30px 0">欢迎登录</div>
    <el-form ref="form"  :model ="form" size ="normal" :rules="rules">
     <el-form-item prop="name">
       <el-input    v-model="form.name" :prefix-icon="User"></el-input>
     </el-form-item>
     <el-form-item prop="password">
       <el-input v-model="form.password" :prefix-icon="Lock" show-password></el-input>
     </el-form-item>
      <el-button style="width: 100% " type="primary" @click="login">登录</el-button>


    </el-form>
  </div>
</div>
   </transition>
</template>

<script>
import { Bowl,UserFilled,User,Lock } from '@element-plus/icons'
import request from "@/utils/request";


export default {

  name: "Login",
  setup() {
    return {
      Bowl,UserFilled,User,Lock
    }
  },
  data(){
    return{
      note: {
        backgroundImage: "url(" + require("./../assets/pic/bg.jpg") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: 100

      },
    form: {},
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
      }
    }
  },
  methods: {
    login(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/user/login", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "登录成功"
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
              console.log("jjjjj"+res.data)
              this.$router.push("/user")  //成功后进行页面跳转到主页
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>