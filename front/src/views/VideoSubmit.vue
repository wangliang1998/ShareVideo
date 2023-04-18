<template>
  <div id="iframeId" style="background: white;margin-top: 70px">
    <div class="panel-head" style="background: white;font-size: medium;margin-left: 15px;"><strong>视频发布</strong></div>
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" style="margin-top: 15px">
      <el-form-item label="视频标题" >
        <el-input v-model="form.title" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="视频简介" >
        <el-input v-model="form.info" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-checkbox-group v-model="form.kind">
          <el-checkbox :label="kind.kind_name + '-' + kind.kind_id" name="type"  v-for="kind in kindData" v-bind:key="kind"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="视频文件">
        <el-upload
            class="upload-demo"
            ref="upload"
            multiple
            accept=".*"
            action=""
            limit="2"
            :with-credentials="true"
            :http-request="httpRequest"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-change="fileChange"
            :on-success="upFile"
            :file-list="fileList"
            :data="getUploadList"
            :auto-upload="false">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip" style="display: inline-block;margin-left: 20px;color: red">先选择封面图片，再选择视频</div>
        </el-upload>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button style="margin-left: 10px;" type="success" @click="submitUploadList">发布</el-button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Element from "_element-ui@2.15.3@element-ui";
export default {
  name: "VideoSubmit",
  data () {
    return {
      form: {
        title: '',
        info:'',
        kind: [],
        pic:'',
        video:''
      },
      kindData:null,
      dialogOfUpload: false,
      fileList: [],
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }
  },
  methods: {

// param是自带参数。 this.$refs.upload.submit() 会自动调用 httpRequest方法.在里面取得file
    httpRequest(param) {
      console.log(param)
    },
    submitUploadList () {
      //this.$refs.upload.submit();
      let fd = new FormData()// FormData 对象
      fd.append('file1', this.fileList[0].raw)// 文件对象
      fd.append('file2', this.fileList[1].raw)// 文件对象
      fd.append('username', localStorage.getItem("username"))
      fd.append('title', this.form.title)
      fd.append('info', this.form.info)
      var kk = this.form.kind.toString().split(',')
      var kinds = []
      for (var i = 0; i < kk.length; i++) {
        kinds.push(kk[i].split('-')[1])
      }
      const data = kinds.toString()
      fd.append('kind', data)
      let url = axios.defaults.baseURL + '/upload'
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios.post(url, fd, config).then(res=>{
        console.log(res)
        if(res.data.code === 200) {
          Element.Message.success('发表成功')
        }
      })
    },
// fileList 是文件列表发生变化后，返回的修改后的列表对象，这里直接赋值就好啦！
    fileChange(file, fileList) {
      this.fileList = fileList
    }
  },
  created() {
    const _this = this
    axios.get(axios.defaults.baseURL + '/api/getKinds').then(function (resp) {
      _this.kindData = resp.data.data
    })
  }
}
</script>

<style scoped>

</style>
