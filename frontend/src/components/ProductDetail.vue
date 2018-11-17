<template>
    <div class="ProductDetail">
        <div class="container" id="product-box">
            <h1>{{product.id}}</h1>
            <div class="columns">
                <div class="column is-one-fifth">
                    <img :src=product.image alt="Placeholder image">
                </div>
                <div class="column">
                    <div class="card">
                        <div class="card-header">
                            {{product.name}}
                        </div>
                        <div class="card-content">
                            {{product.price}}
                        </div>
                        <div class="card-footer">
                            <div class="button" @click="buy()" :disabled="showNavBot">ซื้อสินค้า</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container" id="detail-box">
            <p>รายละเอียดสินค้า</p>
            {{product.detail}}
        </div>
        <div class="navbar is-fixed-bottom" id="nav-bot" v-if="showNavBot">
            <div class="container" id="nav-box" >
                <div class="columns">
                    <div class="column">
                        <ol type="none" > 
                            <p>สินค้า: {{product.name}}</p>
                            <div class="field is-grouped " id="form">
                            <p style="margin-top: 10px;">ส่งสินค้าไปที่:</p>
                            <button class="button is-small is-rounded" id="small-button" @click="editAddress"
                                :disabled="!addresDetail">เปลี่ยนที่อยู่</button>
                            </div>
                            <template v-if="addresDetail">
                                <p class="is-size-7">
                                    {{
                                        address.receiver_name + " " + 
                                        address.tel_no + " " + 
                                        address.receiver_address + " " + 
                                        address.receiver_province + " " + 
                                        address.receiver_postcode 
                                    }}
                                </p>
                            </template>
                            <template v-else>
                                <a @click="showAddressModal" class="is-size-7">+ ใส่ที่อยู่</a>
                            </template>  
                            <div class="field is-grouped " id="form">
                            <p style="margin-top: 13px;"> ชำระเงินด้วย:</p>
                            <button class="button is-small is-rounded" id="small-button" @click="editCreditCard"
                                :disabled="!hasCredit">เปลี่ยนบัตร</button>
                            </div>
                            <template v-if="hasCredit">
                                <a @click="showCreditModal">
                                    <p class="is-size-7">
                                        {{ "XXXX XXXX XXXX X" +credit.id.charAt(13)+credit.id.charAt(14)+credit.id.charAt(15) }}
                                    </p>
                                </a>
                            </template>
                            <template v-else>
                                <a @click="showCreditModal" class="is-size-7">+ ใส่บัตรเครดิต</a>
                            </template>  
                        </ol> 
                    </div>
                    <div class="column">
                        ราคา {{product.price}}
                    </div>
                </div>
            </div>
        </div>
        <!-- popupAddress -->
        <div class="modal-card" id="popUpAddress">
            <div class="container" >
                <div class="modal" id="page-modal2" v-bind:style="{display: showAddress}">
                    <div class="modal-background"></div>
                    <section class="modal-card-body">
                        <div class="modal-content" id="screenAddress">
                            <!-- Address Form start here-->
                            <form>
                            <header id ="head" class="modal-card-head">
                                <p class="modal-card-title">ส่งสินค้าไปที่</p>
                                <button class="delete"  aria-label="close" @click="closeAddressModal"></button>
                            </header>
                            <div class="columns is-multiline" style=" display: flex;" id="bodyPopUpAddress">
                                <div class="field is-horizontal" id="addressbox">
                                    <div class="field" >
                                        <label class="label">ชื่อ - นามสกุล</label>
                                        <div class="control">
                                            <input class="input" type="text" placeholder="Ex. กิตฮับ ขยันอัพจัง" style="width: 300px;"
                                            v-model="address.receiver_name">
                                        </div>
                                    </div>
                                    <div class="field" style="margin-left: 60px;">
                                        <label class="label">หมายเลขโทรศัพท์ (สำหรับติดต่อ)</label>
                                        <div class="control">
                                            <input class="input" type="text" placeholder="Ex. 062-0672928 " style="width: 300px;" 
                                            v-model="address.tel_no">
                                        </div>
                                    </div>
                                </div>
                                <div class="field" id="addressbox-line2">
                                    <label class="label">ที่อยู่ในการจัดส่ง</label>
                                    <div class="control">
                                        <textarea class="textarea" id="address" placeholder="Ex. เช่น 112/44 ตึกหอสมุด ด้านใน KMUTT ถนนสุขสบายดี ตำบลมะละกอ" style="height: 100px; width: 300px;"
                                        v-model="address.receiver_address"></textarea>
                                    </div>
                                </div>
                                <div class="field" style="margin-left: 400px; margin-top: 100px;">
                                    <label class="label">จังหวัด</label>
                                    <div class="control">
                                        <input id="province" class="input" type="text" placeholder="Ex. กรุงเทพมหานคร " style="width: 300px; height: 50px;"
                                        v-model="address.receiver_province">
                                    </div>
                                </div>
                                <div class="field" style="margin-left: 400px; margin-bottom: 2000px;">
                                    <label class="label">หมายเลขไปรษณีย์</label>
                                    <div class="control">
                                        <input id="postcode" class="input" type="text" placeholder="Ex. 10140 " style="width: 300px; height: 50px;"
                                        v-model="address.receiver_postcode">
                                    </div>
                                </div>          
                            </div>
                            <div class="field is-grouped" id="buttonAddress">
                                <footer id= "footer" class="modal-card-foot" >
                                    <button class="button" @click="closeAddressModal" style="width: 100px;">ยกเลิก</button>
                                    <button class="button is-success" @click.prevent="setAddress" style="width: 100px; margin-left: 10px;">บันทึก</button>
                                </footer>
                            </div>
                            </form>
                        </div>
                    </section> 
                </div>
            </div>
        </div>
        <!-- popup Cradit -->
        <div class="modal-card" id="popUpCradit">
            <div class="container" >
                <div class="modal" id="page-modal" v-bind:style="{display: showCredit}">
                    <div class="modal-background"></div>
                    <section class="modal-card-body">
                        <div class="modal-content" id="screen">
                            <header id ="head" class="modal-card-head">
                                <p class="modal-card-title" style="text-align: center;">ใส่บัตรเครดิต/เดบิต</p>
                                <button class="delete"  aria-label="close" @click="closeCreditModal"></button>
                            </header>
                     <!-- form start -->
                             <form>
                            <div class="columns is-multiline" style=" display: flex;" id="bodyPopUp">
                            <div class="field is-grouped" id="card-Pop" >
                                <div class="field is-grouped" id="number" >
                                    <div class="field" >
                                    <label  id="text" class="label">หมายเลขบัตร
                                    <img src="../assets/visa.svg" width="20" height="10" style="  margin-left: 10px;">
                                    <img src="../assets/mastercard.svg" width="20" height="10" style="  margin-left: 10px;"></label>
                                    <div class="control" >
                                        <input id="num" class="input" type="text" placeholder="Ex. 2321 2342 2415 5432"
                                        v-model="credit.id" >
                                    </div>
                                    </div>
                                </div>
                                    <div class="field" id="name" style=" margin-top: 18px;  width: 220px; ">
                                        <label class="label" id="text">ชื่อที่ปรากฏบนบัตร</label>
                                        <div class="control">
                                            <input class="input" type="text" placeholder="Ex. โสภณ จำปาซ่อนกลิ่น" 
                                            v-model="credit.name">
                                        </div>
                                    </div>
                            </div>
                                    <div class="field is-grouped " id="MY">
                                    <div class="field" id="month">
                                        <label  id="text" class="label">เดือนหมดอายุ</label>
                                        <div class="control ">
                                            <input id="box" class="input" type="text" placeholder="mm" style="width: 150px;  margin-right: 50px;"
                                            v-model="credit.exp_m">
                                        </div>
                                    </div>
                                    <div class="field" id="year">
                                        <label  id="text" class="label">ปีหมดอายุ</label>
                                        <div class="control">
                                            <input id="box" class="input" type="text" placeholder="yyyy" style="width: 130px;  margin-right: 50px;"
                                            v-model="credit.exp_y">
                                        </div>
                                    </div>
                                    <div class="field" >
                                        <label  id="text" class="label ">รหัสรักษาความปลอดภัย</label>
                                        <div class="control">
                                            <input id="box" class="input" type="text" placeholder="CVV" style="width: 110px;  margin-right: 50px;"
                                            v-model="credit.cvv">
                                        </div>
                                    </div>
                                    </div>
                                    <div class="field is-grouped " id="foot"> 
                                      <div class="field" >
                                            <button class="button" @click="closeCreditModal">ยกเลิก</button>
                                            <button class="button is-success" @click.prevent="setCreditCard">บันทึก</button>
                                      </div>
                                    </div>
                                </div>    
                            </form>
                             <!-- form end -->    
                        </div>
                    </section>
                </div>
            </div>
        </div>    
    </div>
</template>

<script>
import './../../node_modules/bulma/css/bulma.css';
import axios from 'axios';
import chunk from 'chunk';

const url_test = `http://jsonplaceholder.typicode.com/posts`;
const url_product = `http://localhost:8080/product/`;
const url_credit = `http://localhost:8080/creditcard`;

export default {
    name: 'ProductDetail',
    data () {
        return {
            msg: 'This is Detail page',
            product: {
                id: 0,
                name: '',
                price: '',
                image: '',
                detail: ''
            },
            showNavBot: false,
            showCredit: '',
            showAddress: '',
            addresDetail: false,
            hasCredit: false,
            credit: {
                id: '',
                exp_m: '',
                exp_y: '',
                cvv: '',
                name: '',
                address: '',
                zip: '',
            
            },
            address: {
                receiver_name: '',
                tel_no: '',
                receiver_address: '',
                receiver_province: '',
                receiver_postcode: ''
            
            }
        }
    },
    created() {
        this.product.id = this.$route.params.id;
    },
    mounted() {
        this.getProductDetail();
    },
    methods: {
        getProductDetail: function() {
            axios.get(url_product + this.product.id)
            .then(response => {
                this.product.name = response.data.name,
                this.product.price = response.data.price,
                this.product.image = response.data.img_url,
                this.product.detail = response.data.detail 
            })
        },
        buy: function() {
            this.showNavBot = true;
            
        },
        showAddressModal: function() {
            this.showAddress = 'block';
        },
        closeAddressModal: function() {
            this.showAddress = '';
        },
        showCreditModal: function() {
            this.showCredit = 'block';
        },
        closeCreditModal: function() {
            this.showCredit = '';
        },
        setAddress: function () {
            console.log(this.address.receiver_name + " " + 
                        this.address.receiver_address + " " + 
                        this.address.receiver_province + " " + 
                        this.address.receiver_postcode + " " + 
                        this.address.tel_no);
            axios.post(url_test, {
                receiver_name: this.address.receiver_name,
                tel_no: this.address.tel_no,
                receiver_address: this.address.receiver_address,
                receiver_province: this.address.receiver_province,
                receiver_postcode: this.address.receiver_postcode
            })
            .then(response => {
                console.log(response);
                this.addresDetail = true;
                this.showAddress = '';
            })

        },

        editAddress: function() {
            this.showAddressModal();
        },

        setCreditCard: function() {
            // for tesing
            console.log(this.credit.id + "\n" + this.credit.exp_m + "\n" + this.credit.exp_y + "\n" + this.credit.cvv
             + "\n" + this.credit.name + "\n" + this.credit.address + "\n" + this.credit.zip);
            //  
            axios.post(url_test, {
                card_id: this.credit.id,
                exp_m: this.credit.exp_m,
                exp_y: this.credit.exp_y,
                cvv: this.credit.cvv,
                name: this.credit.name,
                address: this.credit.address,
                zip: this.credit.zip
            })
            .then(response => {
                console.log(response);
                this.hasCredit = true;
                this.showCredit = '';
                this.censorCreditCard;
            })
        },
        editCreditCard: function() {
            this.showCreditModal();
        },

        censorCreditCard: function() {
            credit.id.toString();
            
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


#nav-bot {
    height: 240px;
    border: 0.09em solid #E0E0E0;
}
#nav-top {
  border-bottom: 10px;
  border-color: #E0E0E0;
}

#nav-box {
  width: 1000px;
  margin-top: 20px;
}

#product-box {
  background: white;
  width: 1000px;
  min-height: 300px;
  padding-bottom: 13px;
  box-shadow: 0 4px 15px 0 rgba(40,44,53,.06), 0 2px 2px 0 rgba(40,44,53,.08);
}

#detail-box {
  background: white;
  width: 1000px;
  min-height: 1000px;
  margin-top: 13px;
  box-shadow: 0 4px 15px 0 rgba(40,44,53,.06), 0 2px 2px 0 rgba(40,44,53,.08);
}

html {
    background: #f2f2f2;
}
#num{
    width: 220px;
}

nav {
  background: #f2f2f2;
  border-bottom: 10px;
  border-color: #e0e0e0;
}

#nav-top {
  border-bottom: 10px;
  border-color: #e0e0e0;
}

#nav-box {
  width: 1000px;
}

#form {
    width: 500px;
    height: 35px;
    margin-top: 10px;
}

#card-Pop{
    height: 90px;
    width: 1200px;
    margin-left: 50px;
}
#MY{
    width: 1200px;
    margin-left: 50px
}

#name{
    margin-top: 19px;
    margin-right: 150px;
    padding-right: 30px;
}
#foot{
    width: 800px;
    margin-bottom: 20px;
    justify-content: center;
    margin-top: 10px;
  
}

#bodyPopUp {
  background: white;
  margin-top:0px;
  margin-left: 0px;
  position: fixed;
  height: 250px;
  width: 600px;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;

}


#screen {
  width: 600px;
  margin-top: 10%;

}

#addressbox {
    margin-top: 20px;
    margin-left: 40px;
    position: fixed;
}

#addressbox-line2 {
    margin-top: 100px;
    margin-left: 40px;
    position: fixed;
}

#bodyPopUpAddress {
    text-align: left;
    background: white;
    margin-top:0px;
    margin-left: 0px;
    position: fixed;
    height: 400px;
    width: 800px;
}

#screenAddress {
  width: 800px;
}

#buttonAddress {
    justify-content: center;
    margin-top: 300px;
    margin-right: 60px;
}
#box {
  width: 40%;
  height: 20%;
}

#text {
  font-size: 9pt;
  font-weight: 100;

}
#number {
    height: 9px;
    margin-right: 80px;
    margin-top: 10px;
}
#head {
    border-style: none;
    margin-top: 20px;
}
#footer {
    margin-left: 20px;
    background-color:white; 
    border-style: none;
}

#small-button {
    margin-left: 20px;
    margin-top: 13px;
}

</style>
