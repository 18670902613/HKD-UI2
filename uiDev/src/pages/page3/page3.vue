<template>
    <AutoComplete
        v-model="value1"
        :data="data1"
        @on-search="handleSearch1"
        placeholder="this is page3"
        style="width:200px"></AutoComplete>
</template>
<script>
    export default {
        data () {
            return {
                value1: '',
                data1: []
            }
        },
        methods: {
            handleSearch1 (value) {
                this.data1 = !value ? [] : [
                    value,
                    value + value,
                    value + value + value
                ];
            }
        }
    }
</script>
