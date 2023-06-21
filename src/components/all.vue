<script lang="ts">
import {defineComponent} from 'vue'
import url_json from '../config/url.json'

export default defineComponent({
  data() {
    return {
      url_json: [] as UrlJson
    }
  },
  mounted() {
    this.url_json = url_json
  }
})
</script>

<template>
  <div class="all">
    <div class="left_menu">

    </div>
    <div class="type_block_list">
      <div class="type_block" v-for="type_block in url_json">

        <div class="type_piece">
          <div class="type_icon">
            <img class="type_icon_img" :src="type_block.type_icon" alt="type_icon"/>
          </div>
          <div class="type_name">
            <span class="type_name_span">{{ type_block.type_name }}</span>
          </div>
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

  <p>
    <router-link to="/">Go to Home</router-link>
  </p>
</template>
