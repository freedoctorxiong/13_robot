package com.yscoco.robot.config.pay;

/**
 * @Author: Xiong
 * @Date: 2019/12/6 16:51
 */
public class AlipayConfig {

    // APPID
    public static String app_id = "2016101500695426";

    // 生成公钥时对应的私钥（填自己的）
    public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDxHZihFJP+blyIE8G1Iy16DBSs0gHVS9hW9PO1QP7ijz35Kko83gBqMQ8B6dJWYn7U7MqyTt24UvkC5d2Zp6M9Ee+bqF2KoZbkmDjQcglGIRK/Zek586KkivBL6As3GlyM34MfMzCHNq4od5r/FUJ6AygEQ4eArO+Fm7xiPWdSIznc18bfY0QaEDfcrbnPtE5a8OCPsQajddpawbU5lMdMkMTk/lDc70IIOjTniYuFz3YjgsyFbuLlBDzdrV60gNYaEt9cfAwIU0oXPAlJHP7sJyjg9mXPmCPLaBnf+E5oDrZ7vUk22xv/vYJcDVssmjwDPnlVtv7/0iAxg9zJcnbHAgMBAAECggEBANjs+a6iZuuXuuQqcjJXbcjKKae6YS/XRtIlG0XNLgQDP4x2Qcg1K0Ze0NO13CkgAF/skgiN7+ivrZyv1gkxyylcg6gVd+MLskgDs8nFbxaLFuTf+U7MJV3IKp0ALWbr7qxHQx7E3TopNdLumKI6LE69nDkJ0FUyQWSBUqFIATbg/yUlskQNQ0Je/qktE/fOT3BQhmWRUywvlonGKLzGKucwU+OjZJLYkbujFSowf2WKmEI8FZR1qnssW0R6fO5DWgpi3+6XEpnV+eStCKXGZ8xJckTPhvPbrHPCBvEDk8Ta5nV68Va/XycOr3nvPGUY5VzqonLQVg1Bgg38R1klmQECgYEA/+xtJH0RYWV0D/6I85iVWOMwYYDYIhDo6hQsIodhaMX9BbP/YkSBLEjwZWA67Gveg4P7LkgelapnLe555jsCOaKE9mITgmQa6ftlyNA3OJ10QqvDTyz4POAk2Ym2nxlsf50hmOaMkX5DfVbZuujrZIVVLhT97OA/RwSiChPNDsECgYEA8TAJjf8ESJmYJewA9VQWjDqg5wadzoSybB+XkWpiOiT1vHKRiR5KcfLHPb5RpSmLrBmyf5NT3dUmsxN5LRSms63mmAhHwr/Sn2Z+f60mkIS493X/Rk+HZ4QrXlWImZrmAEzoyVdPtH9idXPNEl+jcDFq9+JmEc//2Oz6dhNqb4cCgYEAq5E1LfQGVdPstdg+uYd9/6P2yDbXLidF7b7uQ4ucmt+4Gz2rJJamPij7uAqGFf7Ooj41grZogo9FMn6gC99dQ4QXPRFM4On5PQkDGFe13Wd5DEUX3xlXpX/iVE/FNO2XVgnh/x33+IW7+ufheTMysyXLj+QUwtsmsJaDhtv1aUECgYBnCo3ac2wCFFSzRtu19CHZ8SZS7Tm4RGrx1KFth9TFYIr34r6YpS9K9Vz33PqGpHQ+p4/h/zBiYrEw6o3nm54Iv6/3nfoCku9KGIDMzU3fikR3vJq+EDYaHJZmrAY7EblXwYPmmP29cHrBESM4yVFLGmHH25qd245DwCLVFUBwuwKBgAgrZ9psgT1ibPoMoPazZ03ex5wctwpBshjVh7c4JuW7Ruv1MYce/l5v4LNwCg+jWse8xDSKfpKSI8qbyrPglnBS/WHc/TB4uy5eE4PpPiQL2ULPLnTIW/hqG3fhV7NtqIhABxkIAjf3EfzF8gG7sSR6Cx9MFF3MrA0LzMCP1yCc";

    //异步回调接口:得放到服务器上，且使用域名解析 IP
    public static String notify_url = "http://www.wysmht.top:10012/notify";


    //支付宝网关（注意沙箱alipaydev，正式则为 alipay）不需要修改
    public static String url = "https://openapi.alipay.com/gateway.do";

    //编码类型
    public static String charset = "UTF-8";

    //数据类型
    public static String format = "json";

    // 公钥
    public static String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm+DpXCUNeOZHwQFsQTJAjuZadMLp7QsSNCj9czqDPhtaCfGZlPZ6UdqEenIgbdYnlEatqW1rfh7IZMpR5jtae95UKpxEOv8Y+wwooy0sJe2XEbOCRVAOLILuy7hM/z1kXTUJtG12qMJD+foOaYEEpA8sZv5baCTz+EZGY3uHXYOFoyDFTBnB7ktdDo0uvVeyNTR+NzFMEKb3Cd+srr5DxFeXULjSAO5XB5s2HMhowaVVtb9X9KgE8gqsANc8nGeewZJ30JsoezErnW8vK2Z8jWLJSupqMMMq1vmvTtstbQ4j88o7HgfJg25vth/vrZqfccvwtLzoDG/FJ85EXGF3YQIDAQAB";

    //签名类型
    public static String signtype = "RSA2";

}
