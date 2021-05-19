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
           <el-button type="primary" @click="dialogFormVisible = true">添加用户</el-button>
        </el-col>
        <!-- 添加用户对话框 -->
       <el-dialog title="添加用户" :visible.sync="dialogFormVisible" width="40%" center @close="closeAddUserDialog">
         <el-form :model="addUserForm" :rules="rules" ref="addUserRuleForm">
           <el-form-item prop="username">
             <el-input prefix-icon="iconfont iconuser" placeholder="用户名" v-model="addUserForm.username" autocomplete="off"></el-input>
           </el-form-item>
           <el-form-item prop="password">
             <el-input prefix-icon="iconfont iconsuo" placeholder="密码" v-model="addUserForm.password" autocomplete="off"></el-input>
           </el-form-item>
           <el-form-item prop="password2">
             <el-input prefix-icon="iconfont iconsuo" placeholder="密码确认" v-model="addUserForm.password2" autocomplete="off"></el-input>
           </el-form-item>
           <el-form-item prop="phone">
             <el-input prefix-icon="el-icon-phone" placeholder="电话" v-model="addUserForm.phone" autocomplete="off"></el-input>
           </el-form-item>
           <el-form-item prop="email">
             <el-input v-model="addUserForm.email" prefix-icon="el-icon-message" placeholder="邮件" autocomplete="off"></el-input>
           </el-form-item>

         </el-form>
         <div slot="footer" class="dialog-footer">
           <el-button @click="dialogFormVisible = false">取 消</el-button>
           <el-button type="primary" @click="addUser">确 定</el-button>
         </div>
       </el-dialog>

       <!-- 修改用户 -->
       <el-dialog title="编辑用户" :visible.sync="dialogUpdateVisible" width="40%" center>
         <el-form :model="updateUserForm" :rules="rules" ref="updateUserRuleForm">
           <el-form-item prop="username">
             <el-input :disabled="true" prefix-icon="iconfont iconuser" placeholder="用户名" v-model="updateUserForm.username" autocomplete="off"></el-input>
           </el-form-item>
           <el-form-item prop="phone">
             <el-input prefix-icon="el-icon-phone" placeholder="电话" v-model="updateUserForm.phone" autocomplete="off"></el-input>
           </el-form-item>
           <el-form-item prop="email">
             <el-input v-model="updateUserForm.email" prefix-icon="el-icon-message" placeholder="邮件" autocomplete="off"></el-input>
           </el-form-item>
         </el-form>
         <div slot="footer" class="dialog-footer">
           <el-button @click="dialogUpdateVisible = false">取 消</el-button>
           <el-button type="primary" @click="updateUser">更新</el-button>
         </div>
       </el-dialog>



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
             <el-button @click="dialogUpdateVisible = true,updateUserForm = scope.row" type="primary" icon="el-icon-edit" circle></el-button>
             <el-button @click="deleteById(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
           </template>
         </el-table-column>
      </el-table>
      <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pageNum"
            :page-sizes="[10, 20, 30, 40]"
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
    const checkEmail = (rule,value,callback) =>{
      const emailRege = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9-_]+\.[a-zA-Z0-9-_]+$/
      if(emailRege.test(value)){
        return callback()
      }
      callback(new Error('请填写正确的邮箱地址'))
    }
    const checkPhone = (rule,value,callback) =>{
      const phoneRege = /^1[3456789][0-9]{9}$/
      if(phoneRege.test(value)){
        return callback()
      }
      callback(new Error('请填写正确的手机号'))
    }
    const checkPassword = (rule,value,callback) =>{
      if(value !== this.addUserForm.password){
        return callback(new Error('2次密码不一致'))
      }
      callback()
    }
    return {
      dialogFormVisible: false,
      dialogUpdateVisible: false,
      userList: [],
      total: 0,
      queryInfo:{
        query: '',
        pageNum: 1,
        pageSize: 5
      },
      addUserForm: {
        username:'',
        password:'',
        password2:'',
        phone:'',
        email:''
      },
      updateUserForm: {
        id:'',
        username:'',
        phone:'',
        email:''
      },
      formLabelWidth: '100px',
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' },
        ],
        password2: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' },
          {validator: checkPassword, trigger:'blur'}
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { min: 11, max: 11, message: '长度必须在11位', trigger: 'blur' },
          {validator: checkPhone, trigger:'blur'}
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { min: 3, max: 30, message: '长度必须在11位', trigger: 'blur' },
          {validator: checkEmail, trigger:'blur'}
        ]
      }
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
    async deleteById(user){
      const result = await this.$confirm('此操作将永久删除用户： ' + user.username+', 是否继续？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).catch(error => error)
      if(result !== 'confirm') return this.$message.info("用户取消操作")

      const { data: resultDel } = await this.$http.put('/user/delete/'+user.id)
      if (resultDel.status == 200) {
        this.getUserList()
        return this.$message.success('删除成功')
      } else return this.$message.error('删除失败')
    },
    closeAddUserDialog(){
      this.$refs.addUserRuleForm.resetFields()
    },
    addUser(){
      this.$refs.addUserRuleForm.validate(async valid =>{
        if(!valid) return this.$message.error("请正确填写")
        const { data: result } = await this.$http.post('/user/addUser',this.addUserForm)
        if (result.status == 200) {
          this.getUserList()
          this.dialogFormVisible = false
          return this.$message.success('添加成功')
        } else return this.$message.error('添加失败')
      })
    },
    updateUser(){
      this.$refs.updateUserRuleForm.validate(async valid =>{
        if(!valid) return thi.$message.error("请重新输入")
        const { data: result } = await this.$http.post('/user/updateUser',this.updateUserForm)
        if (result.status == 200) {
          this.getUserList()
          this.dialogUpdateVisible = false
          return this.$message.success('更新成功')
        } else return this.$message.error('更新失败')
      })
    }
  },
  mounted(){
    this.getUserList()
  }
}
</script>

<style>
</style>
