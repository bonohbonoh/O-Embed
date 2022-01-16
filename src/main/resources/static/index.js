

var main = {

    init: function () {
        var _this = this;
        _this.waitEnter();
    },

    waitEnter: function () {
        if (window.event.keyCode == 13) {
            this.readUrl();
        }
    },

    readUrl: function () {
        let userUrlData = document.getElementById('urlText').value;
        let renderList = document.getElementById('list');
        $.ajax({
            type: 'GET',
            url: '/api/social-embed?url=' + userUrlData,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',

            error: function (err) {
                renderList.innerHTML += "";
            },
            success: function (response) {
                console.log(response)
                renderList.innerHTML = "";
                for (let key in response) {
                    if (key.includes("url") || key.includes("uri")) {

                        renderList.innerHTML += "<tr>" + "<td>" + key + "</td><td><a href='" + response[key] + "'>" + response[key] + "</a></td></tr>";
                        continue;
                    }
                    renderList.innerHTML += "<tr>" + "<td>" + key + "</td><td>" + response[key] + "</td></tr>";
                }
            }
        }).catch((err) => {
            Map = err['responseJSON']
            alert(Map['msg'])
        })

    },


};

main.init();

