<template>
  <div style="padding: 10px">
    <div>
      <el-button type="primary" @click="add()">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>

    </div>
<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load()">查询</el-button>
    </div>
  <el-table :data="tableData" stripe border style="width: 100%">
<!--    <el-table-column prop="date" label="日期" sortable />-->
    <el-table-column prop="name" label="用户名" sortable/>
    <el-table-column prop="nickName" label="昵称" sortable/>
    <el-table-column prop="age" width="80" label="年龄" sortable/>
    <el-table-column  width="80" label="性别"  sortable >
        <template #default="scope">
          <span v-if="scope.row.sex == 0" >女</span>
          <span v-if="scope.row.sex == 1" >男</span>
          <span v-if="scope.row.sex == 2" >曾小贤</span>
        </template>
    </el-table-column>
    <el-table-column prop="address" label="地址" sortable/>
    <el-table-column prop="phone" label="联系方式" sortable/>
    <el-table-column prop="createTime" width="165" label="创建时间" sortable/>
    <el-table-column prop="updateTime" width="165" label="更新时间" sortable/>
    <el-table-column  label="操作" >
      <template #default = "scope">
        <el-button size="mini"   @click="handleEdit(scope.row)">编辑</el-button>
        <el-popconfirm title="你确定要删除吗?" @confirm="handlDelete(scope.row.id)">
          <template #reference>
            <el-button size="mini" type="danger" >删除</el-button>
          </template>
        </el-popconfirm>

      </template>
    </el-table-column>
  </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size = "pageSize"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <el-dialog title="提示"  v-model ="dialogVisible"  width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item  label="用户名">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item  label="昵称">
            <el-input v-model="form.nickName" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item  label="年龄">
            <el-input v-model="form.age" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item  label="性别">
            <el-radio v-model="form.sex" label="1">男</el-radio>
            <el-radio v-model="form.sex" label ="0">女</el-radio>
            <el-radio v-model="form.sex" label ="2">曾小贤</el-radio>
          </el-form-item>
          <el-form-item  label="地址">
            <el-input type=textarea v-model="form.address" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item  label="联系方式">
            <el-input v-model="form.phone" style="width: 80%"></el-input>
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";


export default {
  name: 'User',
  components: {
  },
  data(){
    return {
      form :{},
      search :'',
      dialogVisible :false,
      currentPage:1,
      pageSize:10,
      total:0,
      tableData:[

      ]
    }
  },
  created() {
      this.load();
    },
  methods:{
    //分页
    load(){
      request.get("/user",  {
        params:{
          pageNum :this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
      }).then(res=>{
        console.log(res)
        this.tableData = res.data.records
        this.total =res.data.total
      })
    },
    add(){
      this.dialogVisible = true
      this.form ={}
     },
    save() {
      if(this.form.id){ //如果这个form中有id就走update
        request.put("/user",this.form).then( res =>{
          console.log(res)
          if(res.code === '0'){
            this.$message({
              type:"success",
              message:"修改成功"
            })
          }else {
            this.$message({
              type:"error",
              message:res.msg
            })
          }

        })
      }else {
        request.post("/user",this.form).then( res =>{
          console.log(res)
          if(res.code === '0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })
          }else {
            this.$message({
              type:"error",
              message:res.msg
            })
          }

        })
      }
      this.load()
      this.dialogVisible = false  // 关闭弹窗
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handlDelete(id){
      request.delete("/user/"+id).then(res =>{
        if(res.code === '0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }else {
          this.$message({
            type:"error",
            message:res.msg
          })
        }
        this.load()
      })
    },
    handleSizeChange(pageSize){//改变当前页数
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){  //改变当前页码
      this.currentPage = pageNum
     this.load()
    }
  }
}
</script>
