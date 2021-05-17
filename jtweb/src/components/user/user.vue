<template>
  <div>
    <!-- 卡片 -->
    <el-card class="box-card">

      <el-row :gutter="20">
        <el-col :span="9">
              <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select" clearable @clear="getUserList">
                <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
              </el-input>
        </el-col>
        <el-col :span="4">
           <!-- <el-button type="primary" @click="$router.push('/add')">添加用户</el-button> //-->
           <el-button type="primary" @click="dialogFormVisible = true">添加用户</el-button>

           <el-dialog title="添加用户" :visible.sync="dialogFormVisible" center>
             <el-form :model="form">
               <el-form-item>
                 <el-input prefix-icon="iconfont iconuser" placeholder="用户名" v-model="form.username" autocomplete="off"></el-input>
               </el-form-item>
               <el-form-item>
                 <el-input prefix-icon="el-icon-phone" placeholder="电话" v-model="form.phone" autocomplete="off"></el-input>
               </el-form-item>
               <el-form-item >
                 <el-input v-model="form.email" prefix-icon="el-icon-message" placeholder="邮件" autocomplete="off"></el-input>
               </el-form-item>
               <el-form-item label="状态">
                   <el-switch v-model="form.status"></el-switch>
               </el-form-item>

             </el-form>
             <div slot="footer" class="dialog-footer">
               <el-button @click="dialogFormVisible = false">取 消</el-button>
               <el-button type="primary" @click="addUser">确 定</el-button>
             </div>
           </el-dialog>
        </el-col>
      </el-row>
      <el-table :data="userList" border>
         <el-table-column align = "center" type=index label="序号" width="100"></el-table-column>
         <el-table-column align = "center" prop="username" label="用户名"></el-table-column>
         <el-table-column align = "center" prop="phone" label="电话" ></el-table-column>
         <el-table-column align = "center" prop="email" label="邮件" ></el-table-column>
         <el-table-column align = "center" prop="status" label="状态" >
           <template slot-scope="scope">
             <!-- {{scope.row.stutus}} -->
             <el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ff4949" @change="updateStatus(scope.row)"></el-switch>
           </template>

         </el-table-column>
         <el-table-column align = "center" label="操作" >
           <template slot-scope="scope">
             <el-button @click="" type="primary" icon="el-icon-edit" circle></el-button>
             <el-button @click="deleteById(scope.row.id)" type="danger" icon="el-icon-delete" circle></el-button>
           </template>
         </el-table-column>
      </el-table>
      <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pageNum"
            :page-sizes="[5, 2, 3, 4]"
            :page-size="queryInfo.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
      </div>
    </el-card>

  </div>
</template>

<script>
export default {

  data () {
    return {
      dialogFormVisible: false,
      userList: [],
      total: 0,
      queryInfo:{
        query: '',
        pageNum: 1,
        pageSize: 5
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        username:'',
        phone:'',
        email:'',
        status:''
      },
      formLabelWidth: '100px'
    }
  },
  methods: {
    async getUserList () {
      const { data: result } = await this.$http.get('/user/list',{params: this.queryInfo})
      if (result.status == 200) {
        this.userList = result.data.rows
        this.total = result.data.total
      } else return this.$message.error('获取用户列表错误')
    },
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.getUserList()
    },
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getUserList()
    },
    async updateStatus(user){
      const { data: result } = await this.$http.put('/user/status/'+user.id+'/'+user.status)
      if (result.status == 200) {
        return this.$message.success('修改状态成功')
      } else return this.$message.error('修改状态失败')
    },
    async deleteById(id){
      const { data: result } = await this.$http.put('/user/delete/'+id)

      if (result.status == 200) {
        return this.$message.success('删除成功')
        this.getUserList()
      } else return this.$message.error('删除失败')
    },
    async addUser(){
      const { data: result } = await this.$http.post('/user/add',this.form)
      if (result.status == 200) {
        return this.$message.success('添加成功')
        this.getUserList()
        this.dialogFormVisible = false
      } else return this.$message.error('添加失败')
    }
  },
  mounted(){
    this.getUserList()
  }
}
</script>

<style>
</style>
