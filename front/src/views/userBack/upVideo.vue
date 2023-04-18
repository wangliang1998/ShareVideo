<template>
<div id="iframeId" style="background: white">
  <div class="panel-head" style="background: white;font-size: medium;margin-left: 15px;"><strong>个人视频信息维护</strong></div>
  <el-tabs v-model="activeName" @tab-click="tabClick" style="margin-top: 15px;margin-left: 15px;">
    <el-tab-pane label="上架中" name="first">
      <el-table
          :data="tableData"
          stripe
          border
          :row-style="{height: '0'}"
          :cell-style="{padding: '0'}"
          style="width: 90%;margin-left: 30px;margin-top: 20px;">
        <el-table-column
            :show-overflow-tooltip="true"
            prop="video_id"
            style="text-align: center"
            label="ID"
        >
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="picture"
            style="text-align: center"
            label="封面"
        >
          <template v-slot="scope">
            <el-avatar :size="50" :src="'http://localhost:8888'+scope.row.picture" fit="fill" shape="square"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="title"
            label="标题"
        >
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="info"
            label="简介"
        >
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="time"
            label="发表时间"
        >
        </el-table-column>
        <el-table-column
            label="操作"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="edit(scope.row)" size="large">修改</el-button>
            <el-button type="text" @click="down(scope.row)" size="large" style="color: red">下架</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>

    <el-tab-pane label="下架中" name="second">

      <el-table
          :data="tableData2"
          stripe
          border
          :row-style="{height: '0'}"
          :cell-style="{padding: '0'}"
          style="width: 90%;margin-left: 30px;margin-top: 20px;">
        <el-table-column
            :show-overflow-tooltip="true"
            prop="video_id"
            style="text-align: center"
            label="ID"
        >
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="picture"
            style="text-align: center"
            label="封面"
        >
          <template v-slot="scope">
            <el-avatar :size="50" :src="'http://localhost:8888'+scope.row.picture" fit="fill" shape="square"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="title"
            label="标题"
        >
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="info"
            label="简介"
        >
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="time"
            label="发表时间"
        >
        </el-table-column>
        <el-table-column
            label="操作"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="up(scope.row)" size="large">上架</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-tab-pane>

  </el-tabs>

  <div style="overflow:scroll; width: 100%;height:500px;margin-top: 60px;">
    <div class="panel admin-panel" style="width:100%;float:left;">
      <div class="panel admin-panel">
        <el-dialog title="视频信息修改" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="ID" :label-width="formLabelWidth">
              <el-input v-model="form.video_id" autocomplete="off" readonly="readonly"></el-input>
            </el-form-item>
            <el-form-item label="标题" :label-width="formLabelWidth">
              <el-input v-model="form.title" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="简介" :label-width="formLabelWidth">
              <el-input v-model="form.info" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="editsubmit">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import axios from '_axios@0.21.1@axios'
import Element, { Loading } from 'element-ui'
let loading
export default {
  name: 'updates',
  data () {
    return {
      input: '',
      value: '',
      activeName: 'first',
      tableData: [
      ],
      tableData2: [
      ],
      loading: true,
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        video_id: '',
        title: '',
        info: ''
      },
      formLabelWidth: '100px'
    }
  },
  methods: {
    tabClick (tab, event) {
      var type = tab.name
      if(type==='second'){
        const _this = this
        this.startloading()
        axios.get(axios.defaults.baseURL + '/user/video/submit',{
          params:{
            username:localStorage.getItem("username"),
            state:0
          }
        }).then(function (resp) {
          _this.tableData2 = resp.data.data
          _this.closeloading()
        })
      }else if(type==='first'){
        const _this = this
        this.startloading()
        axios.get(axios.defaults.baseURL + '/user/video/submit',{
          params:{
            username:localStorage.getItem("username"),
            state:1
          }
        }).then(function (resp) {
          _this.tableData = resp.data.data
          _this.closeloading()
        })
      }
    },
    edit (obj) {
      this.dialogFormVisible = true
      this.form.video_id = obj.video_id
      this.form.title = obj.title
      this.form.info = obj.info
    },
    down (obj) {
      const _this = this
      axios.get(axios.defaults.baseURL + '/usr/video/updateState', {
        params: {
          video_id: obj.video_id,
          state:'0'
        }
      }).then(function (resp) {
        _this.$notify({
          title: '提示',
          message: '下架成功',
          duration: 3000
        })
        _this.flush(1)
      })
    },
    up (obj) {
      const _this = this
      axios.get(axios.defaults.baseURL + '/usr/video/updateState', {
        params: {
          video_id: obj.video_id,
          state:'1'
        }
      }).then(function (resp) {
        _this.$notify({
          title: '提示',
          message: '上架成功',
          duration: 3000
        })
        _this.flush(0)
      })
    },
    editsubmit () {
      // 开始提交
      const _this = this
      axios.get(axios.defaults.baseURL + '/usr/video/updateTitle', {
        params: {
          video_id: this.form.video_id,
          title: this.form.title,
          info: this.form.info
        }
      }).then(function (resp) {
        _this.$notify({
          title: '提示',
          message: '修改成功',
          duration: 3000
        })
        _this.flush(1)
      })
      this.dialogFormVisible = false
    },
    startloading () {
      loading = Loading.service({
        lock: true,
        text: '拼命加载中...',
        background: 'rgba(0,0,0,.7)'
      })
    },
    closeloading () {
      loading.close()
    },
    flush (state) {
      const _this = this
      this.startloading()
      axios.get(axios.defaults.baseURL + '/user/video/submit',{
        params:{
          username:localStorage.getItem("username"),
          state:state
        }
      }).then(function (resp) {
        if(state===0){
          _this.tableData2 = resp.data.data
        } else if(state===1){
          _this.tableData = resp.data.data
        }

        _this.closeloading()
      })
    }
  },
  created () {
    const _this = this
    this.startloading()
    axios.get(axios.defaults.baseURL + '/user/video/submit',{
      params:{
        username:localStorage.getItem("username"),
        state:1
      }
    }).then(function (resp) {
      _this.tableData = resp.data.data
      _this.closeloading()
    })
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
  }
}
</script>
<style>
</style>
<style scoped>
@import "../../assets/css/admin.css";
</style>
