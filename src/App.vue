<script lang="ts">
export default {
  data() {
    return {
      //实时屏幕宽度
      windowWidth: document.documentElement.clientWidth,
      //实时屏幕高度
      windowHeight: document.documentElement.clientHeight,
    }
  },
  mounted() {
    // 实时获取浏览器宽度高度
    window.onresize = () => {
      return (() => {
        this.windowWidth = document.documentElement.clientWidth;
        this.windowHeight = document.documentElement.clientHeight;
      })()
    };
  }
}
</script>

<template>
  宽：{{ windowWidth }}
  高：{{ windowHeight }}
  <router-view></router-view>
</template>

<style>
body {
  background-image: url("/background.jpg");
  background-size: 1920px;
  background-repeat: no-repeat;
  background-attachment: fixed;
}
</style>