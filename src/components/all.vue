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
  <div v-for="type_block in url_json">
    {{ type_block.type_name }}
    <span v-for="url_block in type_block.url_list">
      <a :href="url_block.url_link">{{ url_block.url_name }}</a>
    </span>
  </div>

  <p>
    <router-link to="/">Go to Home</router-link>
  </p>
</template>
