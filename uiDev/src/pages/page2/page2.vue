<template>
    <div>
        <AutoComplete
            v-model="value1"
            :data="data1"
            @on-search="handleSearch1"
            placeholder="this is page2"
            style="width:200px">            
        </AutoComplete>
        <Button>Default</Button>
        <Button type="primary">Primary</Button>
        <Button type="ghost">Ghost</Button>
        <Button type="dashed">Dashed</Button>
        <Button type="text">Text</Button>
    </div>
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
