<template>
  <div>
  <el-dialog title="登录" :visible.sync="dialogFormVisible" center>
    <router-view></router-view>
  </el-dialog>
  </div>
</template>

<script>
export default {
  name: "AddUser"
}
</script>

<style>
</style>
