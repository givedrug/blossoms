<script lang="ts">
import type {PropType} from 'vue'
import {defineComponent} from 'vue'
import url_json from '../config/url.json'

export default defineComponent({
  props: {
    url_json: {
      type: Object as PropType<UrlJson>,
      required: true
    }
  },
  data() {
    return {
      url_json: ''
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

  <div v-for="type_block in url_json">
    {{ type_block.type_name }}
  </div>

  <p>
    <router-link to="/">Go to Home</router-link>
  </p>
</template>
