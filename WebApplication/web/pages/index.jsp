<%-- 
    Document   : index
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : Lim Sai Keat
--%>

<%@page import="main.Datas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Home"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>

        <h1 class="text-center">Home pages</h1>
        <div class="container">
            <h2>About Us</h2>
            <h3>Abstract</h3>
            <p>Online payment will be support in version 2.0</p>
            <h3>1. Instagram Feed</h3>
            <p>With an Instagram feed, you can import pictures from your account directly to your blog. We have a plugin suggestion for this to make the process more streamlined.</p>
            <p>This is an especially great idea if you want your brand to be synced across all of your webpages and socials. It's also a good thing to look into if your brand is based on visuals.</p>
            <h3>2. Email Subscribe Form</h3>
            <p>Converting casual visitors into email subscribers builds readership and generates a community of content enthusiasts. Additionally, they'll likely share your content with other people in their networks through email and social media, exposing your blog to brand new audiences.</p>
            <p>However, to turn those visitors into subscribers, an email subscribe form is an absolute must-have for any blog. Make sure you have one highly visible. Considering their importance, you might also want to consider these<a href="#" rel="noopener" target="_blank"> other ways to generate more blog subscribers</a>.</p>
            <h3><span>3. YouTube Gallery</span></h3>
            <p>Do you have a YouTube channel? This is a perfect widget to have, especially if you plan on posting some tutorials on your blog. With a YouTube gallery, you can add videos and comments directly from YouTube onto your blog.</p>
            <p>This could be a helpful widget even if you don't have a YouTube channel. Videos are helpful visual assets, so if you post roundups, you could begin to include videos in them using a YouTube widget. You could also do a funny video of the week to show readers a more fun side of your blog.</p>
            <h3>4. RSS Feed</h3>
            <p>While RSS subscribers tend to be a little bit more passive than email subscribers, there are many people who prefer RSS subscription to email, and they can be beneficial in the same ways as email subscribers. The RSS feed for your blog should be easy to find for visitors who are looking for it (a good place is right near the email subscribe form).</p>
            <h3>5. Testimonials Slider</h3>
            <p>To show appreciation of your customers and boost the credibility of your blog, add in a slider solely focused on testimonials. This is also a great way to spice up your webpages if you believe they're looking a little bare.</p>
            <p>Putting in a slider doesn't crowd your blog, but it does add a little flair. I enjoy reading testimonials on blogs because they give a little insight into the content I might find interesting if I don't have the time to explore every post, so testimonials could be a great asset for lead generation.</p>
            <h3>6. Author Profiles</h3>
            <p>Do you have several people contributing content to your blog? Do you publish content from guest bloggers from time to time? If so, try incorporating author profiles into your blog ? both at the end of individual articles as well as on<a href="#" rel="noopener" target="_blank"> separate author pages</a> that aggregate content published by that author alone.</p>
            <p>Author profiles are a great way to give credit to and promote the hard work of your contributors, provide readers with information about who's creating the content they're reading and allow them to connect, demonstrate the credibility of your writers, and attract additional guest blogging talent.</p>
        </div>
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
    </body>
</html>