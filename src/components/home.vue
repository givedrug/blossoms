<script lang="ts">
import {defineComponent} from 'vue'
import url_json from '../config/url.json'

// 首页每种类型展示url条数
const frequent_number = 7

export default defineComponent({
  data() {
    return {
      url_home: [] as UrlJson
    }
  },
  methods: {
    // 获取每种类型前frequent_number个最常用url，作为常用网址
    getFrequentUrl(url_json: UrlJson): UrlJson {
      let url_frequent: TypeBlock[] = []
      Object.entries(url_json).forEach(([k, v]) => {
        let type_block: TypeBlock = <TypeBlock>{}
        type_block.type_name = v.type_name
        type_block.type_icon = v.type_icon

        // 处理icon url
        Object.entries(v.url_list).forEach(([k, v]) => {
          v.url_link_icon = 'https://www.google.com/s2/favicons?sz=64&domain=' + v.url_link.split('//')[1]
        })

        // 按点击次数排序
        let url_list_sorted: UrlBlock[] = v.url_list.sort((a, b) => b.url_count - a.url_count)
        type_block.url_list = url_list_sorted.slice(0, frequent_number)

        url_frequent.push(type_block)
      })
      return url_frequent
    }
  },
  mounted() {
    this.url_home = this.getFrequentUrl(url_json)
  }
})
</script>

<template>
  <div class="url_frequent">
    <div class="type_block_list">
      <div class="type_block" v-for="type_block in url_home">

        <div class="type_piece">
          <router-link to="/all" class="type_piece_a">
            <div class="type_icon">
              <img class="type_icon_img" :src="type_block.type_icon" alt="type_icon"/>
            </div>
            <div class="type_name">
              <span class="type_name_span">{{ type_block.type_name }}</span>
            </div>
          </router-link>
        </div>

        <div class="url_list">
          <div class="url_piece" v-for="url_block in type_block.url_list">
            <a class="url_piece_a" :href="url_block.url_link">
              <div class="url_icon">
                <img class="url_icon_img" :src="url_block.url_link_icon" alt="url_icon"/>
              </div>
              <div class="url_name">
                <span class="url_name_span">{{ url_block.url_name }}</span>
              </div>
            </a>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>
