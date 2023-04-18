<template>
<div style="background: white;"  id="iframeId">
  <div class="panel-head" style="background: white;font-size: medium;margin-left: 15px;"><strong>用户信息维护</strong></div>
  <div>
    <ul class="search" style="padding-left:10px;border: none;height: 60px">
      <li style="line-height: 40px;font-size: medium">用户名：</li>
      <li>
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </li>
      <li>
        <el-button type="primary" icon="el-icon-search" @click="search()">搜索</el-button>
      </li>
    </ul>
  </div>
  <el-table
      :data="tableData"
      stripe
      border
      :row-style="{height: '0'}"
      :cell-style="{padding: '0'}"
      style="width: 90%;margin-left: 30px;margin-top: 20px;">
    <el-table-column
        :show-overflow-tooltip="true"
        prop="username"
        style="text-align: center"
        label="用户名"
    >
    </el-table-column>
    <el-table-column
        :show-overflow-tooltip="true"
        prop="icon"
        style="text-align: center"
        label="头像"
    >
      <template v-slot="scope">
        <el-avatar :size="50" :src="'http://localhost:8888'+scope.row.icon" fit="fill" shape="square"></el-avatar>
      </template>
    </el-table-column>
    <el-table-column
        :show-overflow-tooltip="true"
        prop="age"
        label="年龄"
    >
    </el-table-column>
    <el-table-column
        :show-overflow-tooltip="true"
        prop="sex"
        label="性别"
    >
    </el-table-column>
    <el-table-column
        :show-overflow-tooltip="true"
        prop="address"
        label="地址"
    >
    </el-table-column>
    <el-table-column
        :show-overflow-tooltip="true"
        prop="phone"
        label="手机"
    >
    </el-table-column>
    <el-table-column
        label="操作"
    >
      <template slot-scope="scope">
        <el-button type="text" @click="edit(scope.row)" size="large">修改</el-button>
        </template>
    </el-table-column>
  </el-table>


  <el-dialog title="修改个人信息" :visible.sync="dialogFormVisible" width="40%">
    <el-form :model="form">
      <el-form-item label="用户名" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off" readonly="readonly"></el-input>
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
      <el-button type="primary" @click="onSubmit">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import axios from 'axios'
import Element from "_element-ui@2.15.3@element-ui";

export default {
  name: 'user',
  data () {
    return {
      user:null,
      input:'',
      dialogFormVisible: false,
      tableData:{},
      form: {
        username:'',
        age: "",
        sex: '',
        address: '',
        phone: ''
      },
      formLabelWidth: '100px',
      fileList: [],
      options: [{
        value: '男',
        label: '男'
      },
        {
          value: '女',
          label: '女'
        }]
    }
  },
  methods: {
    update () {
      this.dialogFormVisible = true
    },
    onSubmit () {
      const _this = this
      let fd = new FormData()// FormData 对象
      if(this.fileList.length>0){
        fd.append('file1', this.fileList[0].raw)// 文件对象
      }
      fd.append('username', this.form.username)
      fd.append('sex', this.form.sex)
      fd.append('age', this.form.age)
      fd.append('address', this.form.address)
      fd.append('phone', this.form.phone)
      let url = axios.defaults.baseURL + '/user/update'
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios.post(url, fd, config).then(res=>{
        console.log(res)
        if(res.data.code === 200) {
          Element.Message.success('修改成功')
          this.dialogFormVisible = false
        }
      })
    },
    edit (obj) {
      this.dialogFormVisible = true
      this.form.username = obj.username
      this.form.age = obj.age
      this.form.sex = obj.sex
      this.form.address = obj.address
      this.form.phone = obj.phone
    },
    search () {
      const _this = this
      axios.get(axios.defaults.baseURL + '/back/getUser',{
        params:{
          username: this.input
        }
      }).then(function (resp) {
        _this.tableData = resp.data.data
      })
    }
  },
  mounted () {
    /**
     * iframe-宽高自适应显示
     */
  },
  created() {
    // const _this = this
    // axios.get(axios.defaults.baseURL + '/getUser',{
    //   params:{
    //     username: localStorage.getItem('username')
    //   }
    // }).then(function (resp) {
    //   _this.user = resp.data.data
    //   _this.form.age = _this.user.age
    //   _this.form.sex = _this.user.sex
    //   _this.form.address = _this.user.address
    //   _this.form.phone = _this.user.phone
    // })
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 480px;
}
</style>
