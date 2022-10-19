<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #CCC;display: flex">
    <div style="width: 200px;padding-left: 30px ;font-weight: bold;color: darkorange">丁557的BORK</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;padding-top: 15px;padding-right: 25px" >
      <el-dropdown>
    <span style="position: relative; top: 5px">
           {{ user.name }}
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

  </div>


</template>

<script>
export default {
  name: "Header",

  //props: ['user'],
  data() {
    return {
      user: {}
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
  }
}
</script>

<style scoped>

</style>