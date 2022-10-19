<template>
  <nav>
    <!--    定义头部-->
    <Header :user="user"/>
    <div style="display: flex">
      <Aside/> <!--侧边栏-->
      <router-view style="flex: 1"></router-view>    <!--主体-->
    </div>

  </nav>
</template>


<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {

  name: "Layout",
  components: {
    Header,
    Aside
  },
  data() {
    return {
      user: {}
    }
  },
  created() {
    this.refreshUser()
  },
  methods: {
    refreshUser() {
      let userJson = sessionStorage.getItem("user");
    }
  }
}
</script>

<style scoped>

</style>