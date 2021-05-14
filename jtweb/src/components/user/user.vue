<template>
  <el-table
      :data="userList"
      border>
      <el-table-column
        prop="id"
        label="id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话"
        width="180">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮件"
        width="180">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="180">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
</template>

<script>
export default {
  created () {
    this.getUserList()
  },
  data () {
    return {
      userList: []
    }
  },
  methods: {
    async getUserList () {
      const { data: result } = await this.$http.get('/user/getUserList')
      if (result.status == 200) { this.userList = result.data } else return this.$message.error('获取用户列表错误')
    }
  }
}
</script>

<style>
</style>
