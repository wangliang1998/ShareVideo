<template>
  <div id="iframeId" style="background: ghostwhite">

    <div id="id_test_video"></div>
    <el style="margin-top: 45px;margin-left: 20px;float: left;font-size: larger">视频发布者：</el>
    <div>
      <el-avatar :size="70" :src="circleUrl" style="text-align: left;margin-top: 20px;margin-left: 20px;float: left"></el-avatar>
      <el-link type="danger" style="margin-top: 45px;margin-left: 20px;float: left;font-size: larger" @click="center">{{this.user.username}}</el-link>
    </div>
    <div class="bb-ys">
      <div id="comment" class="comment"><section class="comment sec" id="cmt_anchor">
        <div id="SOHUCS">
          <div id="SOHU_MAIN">
            <div class="module-cmt-header">
              <div class="clear-g section-title-w">
                <div class="title-user-w">
                  <div node-type="user" class="clear-g user-wrap-w">
                    <span node-type="user-name" class="wrap-name-w"></span>
                  </div>
                </div>
              </div>
              <div class="section-cbox-w">
                <div class="cbox-block-w clear-g">
                  <div node-type="block-head-w" class="block-head-w">
                    <div node-type="header-login" class="header-login">
                      <input style="border: 0px; cursor: pointer; background-color:transparent;height: 40px; line-height: 40px; margin:-4px 0 0 6px;vertical-align:middle; color:#e74851; font-size:14px; " type="text" id="c_name" name="c_name" :value="username" disabled="disabled">
                    </div>
                  </div>
                  <div node-type="login-select" class="block-post-w">
                    <div class="module-cmt-box">
                      <div class="post-wrap-w">
                        <div class="post-wrap-border-l"></div>
                        <div class="post-wrap-border-r"></div>
                          <div node-type="post-wrap-main" class="post-wrap-main">
                            <div class="post-wrap-border-t">
                              <div node-type="post-wrap-border-t-l" class="post-wrap-border-t-l"></div>
                              <div node-type="post-wrap-border-t-r" class="post-wrap-border-t-r"></div>
                            </div>
                            <div class="wrap-area-w">
                              <div class="area-textarea-w">
                                <textarea node-type="textarea" id="c_content" name="c_content" class="textarea-fw textarea-bf" placeholder="请输入评论内容" onfocus="this.style.backgroundImage='';" v-model="neirong"></textarea>
                              </div>
                            </div>
                          </div>
                          <div class="clear-g wrap-action-w">
                            <div class="clear-g action-issue-w">
                              <div class="issue-btn-w">
                                <input class="cmt_post" type="button" value="发布" @click="submit">
                              </div>
                            </div>
                          </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <ul>
          <li v-for="item in comments" v-bind:key="item">
            <div class="cmt_msg" id="cmt_msg">
              <div class="cmt_wrap">
                <div class="cmt_item clearfix">
                  <span class="face_wrap fl"><img :alt="item.user_id" class="facepl" :src="'http://localhost:8888' + item.user_pic"></span>
                  <div class="item_con fl">
                    <p class="top1">
                      <span class="name">{{item.user_id}}</span>
                      ({{ item.time }})
                    </p>
                    <p class="con">{{item.contain}}</p>

                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </section></div>
    </div>
  </div>
</template>
<script>
import axios from '_axios@0.21.1@axios'
import { VTcPlayer } from 'v-tcplayer'
import Element from "_element-ui@2.15.3@element-ui";
export default {
  name: 'information',
  data () {
    return {
      circleUrl:"",
      video: {
      },
      user:{

      },
      comments:null,
      username:localStorage.getItem('username'),
      neirong:""
    }
  },
  methods: {
    center () {
      window.open('/center?userid=' + this.user.username,'_blank')
    },
    submit () {
      axios.get(axios.defaults.baseURL + '/video/comment',{
        params:{
          username:this.username,
          video_id:this.video.video_id,
          contain:this.neirong
        }
      }).then(function (resp) {
        Element.Message.success('发表成功')
      })
    }
  },
  created () {
    const id = this.$route.params.id
    const _this = this
    axios.get(axios.defaults.baseURL + '/video/detail?id=' + id).then(function (resp) {
      _this.video = resp.data.data.video
      _this.user = resp.data.data.user
      _this.comments = resp.data.data.comments
      document.title = '视频简介：' + _this.video.title
      _this.circleUrl = "http://localhost:8888"+_this.user.icon
      var player = new TcPlayer("id_test_video", {
        mp4: "http://localhost:8888" + _this.video.video_url, //请替换成实际可用的播放地址
        autoplay: false, //iOS 下 safari 浏览器，以及大部分移动端浏览器是不开放视频自动播放这个能力的
        poster: "",
        width: "100%", //视频的显示宽度，请尽量使用视频分辨率宽度
        height: 500 //视频的显示高度，请尽量使用视频分辨率高度
      });
    })
  },
  beforeCreate() {

  },
  mounted () {

  }
}
</script>
<style scoped>
@import "../assets/css/style.css";
@import "../assets/css/yan.css";
</style>
