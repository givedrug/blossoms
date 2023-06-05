<script lang="ts">
import type {PropType} from 'vue'
import {defineComponent} from 'vue'
import url_json from '../config/url.json'

export default defineComponent({
  props: {
    url_home: {
      type: Object as PropType<UrlJson>,
      required: true
    }
  },
  data() {
    return {
      url_home: ''
    }
  },
  methods: {
    getFrequentUrl(url_json) {
      url_json.forEach((type_block) => {
        console.log(type_block)
      })
      return url_json
    }
  },
  mounted() {
    this.url_home = this.getFrequentUrl(url_json)
  }
})
</script>

<template>
  <div v-for="type_block in url_home">
    {{ type_block.type_name }}
  </div>

  <p>
    <router-link to="/">Go to Home</router-link>
    <router-link to="/all">Go to All</router-link>
  </p>
</template>
