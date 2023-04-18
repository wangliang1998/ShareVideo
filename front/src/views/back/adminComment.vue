<template>
<div id="iframeId" style="background: white">
  <div class="panel-head" style="background: white;font-size: medium;margin-left: 15px;"><strong>评论信息维护</strong></div>
  <div>
    <ul class="search" style="padding-left:10px;border: none;height: 60px">
      <li style="line-height: 40px;font-size: medium">用户名：</li>
      <li>
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </li>
      <li>
        <el-button type="primary" icon="el-icon-search" @click="search()">搜索用户评论</el-button>
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
            prop="id"
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
            label="评论时间"
        >
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="contain"
            label="评论内容"
        >
        </el-table-column>
        <el-table-column
            label="操作"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="edit(scope.row)" size="large">修改</el-button>
            <el-button type="text" @click="down(scope.row)" size="large" style="color: red">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

  <div style="overflow:scroll; width: 100%;height:500px;margin-top: 60px;">
    <div class="panel admin-panel" style="width:100%;float:left;">
      <div class="panel admin-panel">
        <el-dialog title="评论信息修改" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="ID" :label-width="formLabelWidth">
              <el-input v-model="form.id" autocomplete="off" readonly="readonly"></el-input>
            </el-form-item>
            <el-form-item label="评论内容" :label-width="formLabelWidth">
              <el-input v-model="form.contain" autocomplete="off"></el-input>
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
      tableData: [
      ],
      loading: true,
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        id: '',
        contain: ''
      },
      formLabelWidth: '100px'
    }
  },
  methods: {
    edit (obj) {
      this.dialogFormVisible = true
      this.form.id = obj.id
      this.form.contain = obj.contain
    },
    down (obj) {
      const _this = this
      axios.get(axios.defaults.baseURL + '/usr/comment/delete', {
        params: {
          id: obj.id
        }
      }).then(function (resp) {
        _this.$notify({
          title: '提示',
          message: '删除成功',
          duration: 3000
        })
        _this.flush()
      })
    },
    editsubmit () {
      // 开始提交
      const _this = this
      axios.get(axios.defaults.baseURL + '/usr/comment/update', {
        params: {
          id: this.form.id,
          contain: this.form.contain
        }
      }).then(function (resp) {
        _this.$notify({
          title: '提示',
          message: '修改成功',
          duration: 3000
        })
        _this.flush()
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
    search () {
      const _this = this
      this.startloading()
      axios.get(axios.defaults.baseURL + '/back/comment/search',{
        params:{
          username:this.input
        }
      }).then(function (resp) {
        _this.tableData = resp.data.data
        _this.closeloading()
      })
    }
  },
  created () {

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
