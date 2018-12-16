<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>

    <!-- Basic Page Needs
  ================================================== -->
    <meta charset="utf-8">
    <title>zGoodFood</title>
    <meta name="description" content="Free Responsive Html5 Css3 Templates | zerotheme.com">
    <meta name="author" content="www.zerotheme.com">

    <!-- Mobile Specific Metas
	================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- CSS
	================================================== -->
    <link rel="stylesheet" href="/css/zerogrid.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/lightbox.css">

    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


    <link rel="stylesheet" href="/css/menu.css">
    <script src="/js/jquery1111.min.js" type="text/javascript"></script>
    <script src="/js/script.js"></script>
    <script src="/js/ajaxes.js"></script>
    <script src="/js/loadCart.js"></script>

    <!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/Items/ie/home?ocid=ie6_countdown_bannercode">
            <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="/js/html5.js"></script>
    <script src="/js/css3-mediaqueries.js"></script>
    <![endif]-->

</head>

<body onload="auth(), loadCart()">
<div class="wrap-body">
    <header class="zerogrid">
        <div class="logo">
            <hr class="line-1">
            <a href="#">zGoodFood</a>
            <span>Lorem ipsum dolor sit amet</span>
            <hr class="line-1">
        </div>
        <div id='cssmenu' class="align-center">
            <ul>

                <li><a href='/main'><span>Home</span></a></li>
                <li><a href='/menu'><span>Menu</span></a></li>
                <li class="active"><a href='/cart'><span>Cart</span></a></li>
                <li class='last' id="login"><a href='/login'><span>Login or Register</span></a></li>

            </ul>
        </div>
    </header>
    <!--////////////////////////////////////Container-->
    <section id="container">
        <div class="wrap-container clearfix">
            <div id="main-content">

                <div class="wrap-content zerogrid" id="cart">

                </div>
            </div>
        </div>
    </section>
    <hr class="line">
    <!--////////////////////////////////////Footer-->
    <footer>
        <div class="wrap-footer">
            <div class="zerogrid">
                <div class="row">
                    <div class="col-1-3">
                        <div class="wrap-col">
                            <p>Copyright - Designed by <a href="https://www.zerotheme.com" title="free website templates">ZEROTHEME</a></p>
                        </div>
                    </div>
                    <div class="col-1-3">
                        <div class="wrap-col">
                            <ul class="social-buttons">
                                <li><a href="#"><i class="fa fa-twitter"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-facebook"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-1-3">
                        <div class="wrap-col">
                            <ul class="quick-link">
                                <li><a href="#">Privacy Policy</a></li>
                                <li><a href="#">Terms of Use</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <script src="/js/lightbox-plus-jquery.min.js"></script>
</div>
</body>
</html>