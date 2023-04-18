<template>
<div id="iframeId" style="background: white">
  <div class="panel-head" style="background: white;font-size: medium;margin-left: 15px;"><strong>视频信息维护</strong></div>
  <div>
    <ul class="search" style="padding-left:10px;border: none;height: 60px">
      <li style="line-height: 40px;font-size: medium">视频名称：</li>
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
            <el-avatar :size="100" :src="'http://localhost:8888'+scope.row.picture" fit="fill" shape="square"></el-avatar>
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
            :show-overflow-tooltip="true"
            prop="state"
            label="状态"
        >
          <template slot-scope="scope">
            <h3>{{scope.row.state===1?'上架中':'下架中'}}</h3>
          </template>
        </el-table-column>
        <el-table-column
            label="操作"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="edit(scope.row)" size="large">修改</el-button>
          </template>
        </el-table-column>
      </el-table>


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
            <el-form-item label="状态" :label-width="formLabelWidth">
              <el-select v-model="form.state===1?'上架中':'下架中'" placeholder="请选择" size="medium">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
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
        info: '',
        state:'上架中'
      },
      options: [{
        value: '1',
        label: '上架中'
      },
        {
          value: '0',
          label: '下架中'
        }],
      formLabelWidth: '100px'
    }
  },
  methods: {
    search () {
      const _this = this
      axios.get(axios.defaults.baseURL + '/back/video/search',{
        params:{
          name: this.input
        }
      }).then(function (resp) {
        _this.tableData = resp.data.data
      })
    },
    edit (obj) {
      this.dialogFormVisible = true
      this.form.video_id = obj.video_id
      this.form.title = obj.title
      this.form.info = obj.info
      this.form.state = obj.state
    },
    editsubmit () {
      // 开始提交
      const _this = this
      axios.get(axios.defaults.baseURL + '/back/video/update', {
        params: {
          video_id: this.form.video_id,
          title: this.form.title,
          info: this.form.info,
          state:this.form.state
        }
      }).then(function (resp) {
        _this.$notify({
          title: '提示',
          message: '修改成功',
          duration: 3000
        })
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
