<template>
  <div id="iframeId">
    <el-container>
      <el-header style="background:black" height="70">
        <div  style="width: 100%;">
          <div class="logo margin-big-left fadein-top" style="width: 100%;text-align: left">
            <h1 style="margin-left: 10px;color: white;float: left">后台管理系统</h1>
          </div>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px" id="el-aside">
          <el-row class="tac">
            <el-col :span="12" style="width: 200px">
              <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                background-color="black"
                text-color="#fff"
                active-text-color="#ffd04b"
                @open="handleOpen"
                @close="handleClose">
                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-edit-outline"></i>
                    <span>用户管理</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="1-1" @click="to(1)"><i class="el-icon-user"></i>用户信息维护</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-submenu index="2">
                  <template slot="title">
                    <i class="el-icon-edit-outline"></i>
                    <span>视频管理</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="2-2" @click="to(3)"><i class="el-icon-video-camera"></i>视频信息维护</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-submenu index="3">
                  <template slot="title">
                    <i class="el-icon-edit-outline"></i>
                    <span>评论管理</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="3-1" @click="to(4)"><i class="el-icon-video-camera"></i>评论信息维护</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main style="overflow-y: hidden">
          <iframe style="border: none;overflow-y: hidden"  rameborder="0" :src="url" width="100%" height="100%"></iframe>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Element from 'element-ui'
import axios from "_axios@0.21.1@axios";

export default {
  name: 'back',
  data () {
    return {
      height: '600px',
      url: '',
    }
  },
  methods: {
    to (index) {
      if (index === 1) {
        this.url = '/admin/user'
      } else if (index === 2) {
        this.url = '/center/?userid=' + localStorage.getItem('username')
      } else if (index === 3) {
        this.url = '/admin/video'
      } else if (index === 4) {
        this.url = '/admin/comment'
      }
    }
  },
  created () {
    document.title = '后台管理系统'
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
    document.getElementById('el-aside').style.height = (Number(deviceHeight) - 70) + 'px'
    document.getElementById('el-aside').style.background = 'black'
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 70px;
  height: 70px;
}

.el-aside {
  background-color: black;
  color: #333;
  line-height: 500px;
}

.el-main {
  background-color: white;
  color: #333;
  text-align: left;
  line-height: 500px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
