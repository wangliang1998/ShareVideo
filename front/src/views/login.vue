<template>
<div id="iframeId" style="text-align: center;background: white">
  <div id="login">
    <h1><img src="../assets/img/video_player.png" width="100" height="100"></h1>
    <h1 style="font-size: large">请登录</h1>
    <input type="text" required="required" placeholder="用户名" v-model="username">
    <input type="password" required="required" placeholder="密码" v-model="password">
    <button class="but" @click="login">登录</button>
    <button class="but2" @click="register">注册</button>
  </div>

  <el-dialog title="注册" :visible.sync="dialogFormVisible" width="40%">
    <el-form :model="form">
      <el-form-item label="用户名" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="form.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="年龄" :label-width="formLabelWidth">
        <el-input v-model="form.age" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-select v-model="form.sex" placeholder="请选择" size="medium">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="地址" :label-width="formLabelWidth">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="手机" :label-width="formLabelWidth">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="editsubmit">确 定</el-button>
    </div>
  </el-dialog>

</div>
</template>
<script>

import axios from '_axios@0.21.1@axios'
import Element from 'element-ui'
export default {
  name: 'login',
  data () {
    return {
      options: [{
        value: '男',
        label: '男'
      },
        {
          value: '女',
          label: '女'
        }],
      username: '',
      password: '',
      dialogFormVisible: false,
      form: {
        username: '',
        password: '',
        age: "",
        sex: '',
        address: '',
        phone: ''
      },
      formLabelWidth: '100px'
    }
  },
  methods: {
    editsubmit () {
      const _this = this
      const params = new URLSearchParams();
      params.append('username', this.form.username);
      params.append('password', this.form.password);
      params.append('age', this.form.age);
      params.append('sex', this.form.sex);
      params.append('address', this.form.address);
      params.append('phone', this.form.phone);
      axios.get("/usr/zhuce",{
        params:params
      }).then(function (resp) {
        var code = resp.data.code
        if(code === 200 ){
          Element.Message.success('注册成功')
          _this.dialogFormVisible = false
        } else {
          Element.Message.error('注册失败')
        }
      })
    },
    register () {
      this.dialogFormVisible = true
    },
    login () {
      if (this.username === '' || this.password === '') {
        Element.Message.error('用户名或密码不能为空')
        return
      }
      const _this = this
      axios.get(axios.defaults.baseURL + '/usr/login', {
        params: {
          username: _this.username,
          password: _this.password
        }
      }).then(function (resp) {
        var code = resp.data.code
        if(code === 0 ){
          Element.Message.error('用户名或密码错误')
        }else if(code===1){
          _this.$store.commit('SET_USERNAME', _this.username)
          _this.$store.commit('SET_ICON',resp.data.data.user.icon)
          _this.$router.push('/movies')
        } else if(code===2){
          _this.$router.push('/admin/back')
        }
      })
    }
  },

  mounted () {
    /**
     * iframe-宽高自适应显示
     */
    const oIframe = document.getElementById('iframeId')
    const deviceWidth = document.documentElement.clientWidth
    const deviceHeight = document.documentElement.clientHeight
    oIframe.style.width = (Number(deviceWidth)) + 'px'
    oIframe.style.height = (Number(deviceHeight)) + 'px'
  },
  created () {
  }

}
</script>
<style scoped>
#login{
  position: absolute;
  top: 50%;
  left:50%;
  margin: -150px 0 0 -150px;
  width: 300px;
  height: 300px;
}
#login h1{
  color: black;
  letter-spacing: 1px;
  text-align: center;
}
h1{
  font-size: 15px;
  margin: 0.67em 0;
}
input{
  width: 278px;
  height: 25px;
  margin-bottom: 10px;
  outline: none;
  padding: 10px;
  font-size: 13px;
  color: black;
  text-shadow:1px 1px 1px;
  border-top: 1px solid #312E3D;
  border-left: 1px solid #312E3D;
  border-right: 1px solid #312E3D;
  border-bottom: 1px solid #56536A;
  border-radius: 4px;
  background-color: white;
}
.but{
  width: 270px;
  min-height: 25px;
  display: block;
  background-color: #4a77d4;
  border: 1px solid #3762bc;
  color: #fff;
  padding: 9px 14px;
  font-size: 15px;
  line-height: normal;
  border-radius: 5px;
  margin: 0;
}
.but2{
  margin-top: 10px;
  width: 270px;
  min-height: 25px;
  display: block;
  background-color: palevioletred;
  border: 1px solid #3762bc;
  color: #fff;
  padding: 9px 14px;
  font-size: 15px;
  line-height: normal;
  border-radius: 5px;
}
</style>
