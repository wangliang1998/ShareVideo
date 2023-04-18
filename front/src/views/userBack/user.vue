<template>
<div style="background: white;"  id="iframeId">
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>个人信息</span>
      <el-button style="float: right; padding: 3px 0" type="text" @click="update">修改信息</el-button>
    </div>
    <div class="text item">
      用户名：{{user.username}}
    </div>
    <div class="text item">
      性别：{{user.sex}}
    </div>
    <div class="text item">
      年龄：{{user.age}}
    </div>
    <div class="text item">
      地址：{{user.address}}
    </div>
    <div class="text item">
      手机：{{user.phone}}
    </div>
    <div class="text item">
      注册时间：{{user.register_time}}
    </div>
  </el-card>
  <el-dialog title="修改个人信息" :visible.sync="dialogFormVisible" width="40%">
    <el-form :model="form">
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
      <el-form-item label="头像">
        <el-upload
            class="upload-demo"
            ref="upload"
            multiple
            accept=".png,.jpg,.jpeg,.icon"
            action=""
            limit="1"
            :with-credentials="true"
            :http-request="httpRequest"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-change="fileChange"
            :on-success="upFile"
            :file-list="fileList"
            :data="getUploadList"
            :auto-upload="false">
          <el-button slot="trigger" size="small" type="primary">选取图片</el-button>
        </el-upload>
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
      dialogFormVisible: false,
      form: {
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
      fd.append('username', localStorage.getItem("username"))
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
    fileChange(file, fileList) {
      this.fileList = fileList
    }
  },
  mounted () {
    /**
     * iframe-宽高自适应显示
     */
  },
  created() {
    const _this = this
    axios.get(axios.defaults.baseURL + '/getUser',{
      params:{
        username: localStorage.getItem('username')
      }
    }).then(function (resp) {
      _this.user = resp.data.data
      _this.form.age = _this.user.age
      _this.form.sex = _this.user.sex
      _this.form.address = _this.user.address
      _this.form.phone = _this.user.phone
    })
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
