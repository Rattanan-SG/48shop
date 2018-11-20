<template>
  <div class="container">
    <div class="columns">
      <div class="column is-offset-1 is-5">
        <span class="title is-3">Order Detail:</span>
      </div>
    </div>
    <div class="columns">
      <div class="column is-offset-1">
        <div class="box">
          <div class="columns">
            <div class="column">
              Order #{{order.id}}
            </div>
          </div>
          <div class="columns">
            <div class="column">
              Place on: {{createdAt}}
            </div>
          </div>

        </div>
      </div>
    </div>
    <div class="columns">
      <div class="column is-offset-1">
        <div class="box">
          <div class="columns">
            <div class="column is-2">
              <img :src='order.product.img_url' alt="img" />
            </div>
            <div class="column is-10">
              <div class="columns">
                <div class="column is-12">
                  <span class="title is-4">
                    {{order.product.name}}
                  </span>
                </div>
              </div>
              <div class="columns">
                <div class="column is-2">
                  ชื่อผู้รับ:
                </div>
                <div class="column is-10">
                  <span class="title is-6">
                    {{order.destination.receiverName}}
                  </span>
                </div>
              </div>
              <div class="columns">
                <div class="column is-2">
                  ที่อยู่ปลายทาง:
                </div>
                <div class="column is-10">
                  <span class="title is-6">
                    {{order.destination.detail + ' ' + order.destination.city + ' ' + order.destination.zipcode}}
                  </span>
                </div>
              </div>
              <div class="columns">
                <div class="column is-2">
                  หมายเลขโทรศัพท์:
                </div>
                <div class="column is-10">
                  <span class="title is-6">
                    {{order.destination.telNumber}}
                  </span>
                </div>
              </div>
              <div class="columns">
                <div class="column is-offset-6 is-2">
                  <span class="title is-4">
                    ราคารวม
                  </span>
                </div>
                <div class="column is-2">
                  <span class="title is-4">
                    {{order.product.price}} x {{order.productQuantity}}
                  </span>
                </div>
                <div class="column is-2">
                  <span class="title is-4">
                    {{order.product.price * order.productQuantity}} ฿
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="columns">
      <div class="column is-offset-10 is-2">
        <a class="button" style="background-color: #714ec9; color: white" @click='goToHome'>ช๊อปต่อเลย</a>
      </div>
    </div>
  </div>
</template>
<script>
import axios from '@/utils/axios'
import { mapGetters } from 'vuex';

const URL_ORDER = 'http://localhost:8080/order/'
export default {
  name: 'SummaryOrder',
  data: () => ({
    order: {},
    createdAt: ''
  }),
  methods: {
    async getOrder () {
      const { data } = await axios.get(`/order/${this.getOrderId}`)
      this.order = data
      let date = new Date(data.createdAt)
      this.createdAt = date.getDate() + ' / ' + (date.getMonth()+1) + ' / ' + date.getFullYear()
    },
    goToHome () {
      this.$router.push('/home')
    }
  },
  mounted () {
    this.getOrder()
  },
  computed: {
    ...mapGetters([
      'getOrderId'
    ])
  }
}
</script>
