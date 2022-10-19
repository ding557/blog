/** 2022/3/17
* 作者:丁557
* 功能:左侧导航菜单
*/

  <template>
<!--    <el-row class="tac">-->

<!--        <el-menu-->
<!--            default-active="user"-->
<!--            class="el-menu-vertical-demo"-->
<!--            style="width: 200px ;min-height: calc(100vh - 50px)"-->
<!--        >-->
<!--          <el-sub-menu index="1">-->
<!--            <template #title>-->
<!--              <el-icon><location /></el-icon>-->
<!--              <span>系统管理</span>-->
<!--            </template>-->
<!--            <el-menu-item-group title="">-->
<!--              <el-menu-item index="user">用户管理</el-menu-item>-->
<!--            </el-menu-item-group>-->

<!--          </el-sub-menu>-->
<!--        </el-menu>-->

<!--    </el-row>-->
  <div>
    <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      default-active="user"
      router
      :default-openeds="[1]"
      class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>用户管理</template>
        <el-menu-item index="user">用户管理</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="book" >论坛</el-menu-item>
    </el-menu>

  </div>
  </template>


  <script >
  export default {
    name: "Aside",
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