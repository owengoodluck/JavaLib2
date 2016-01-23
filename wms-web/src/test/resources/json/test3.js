P.when('A').register(
				"ImageBlockATF",
				function(A) {
					var data = {
						'colorImages' : {
							'initial' : [
									{
										"hiRes" : "http://ecx.images-amazon.com/images/I/6195qNMJW1L._SL1000_.jpg",
										"thumb" : "http://ecx.images-amazon.com/images/I/51NuqsGGL3L._SS40_.jpg",
										"large" : "http://ecx.images-amazon.com/images/I/51NuqsGGL3L.jpg",
										"main" : {
											"http://ecx.images-amazon.com/images/I/6195qNMJW1L._SY355_.jpg" : [
													355, 355 ],
											"http://ecx.images-amazon.com/images/I/6195qNMJW1L._SY450_.jpg" : [
													450, 450 ],
											"http://ecx.images-amazon.com/images/I/6195qNMJW1L._SX425_.jpg" : [
													425, 425 ],
											"http://ecx.images-amazon.com/images/I/6195qNMJW1L._SX466_.jpg" : [
													466, 466 ],
											"http://ecx.images-amazon.com/images/I/6195qNMJW1L._SX522_.jpg" : [
													522, 522 ]
										},
										"variant" : "MAIN"
									},
									{
										"hiRes" : null,
										"thumb" : "http://ecx.images-amazon.com/images/I/51c1%2BwNe2tL._SS40_.jpg",
										"large" : "http://ecx.images-amazon.com/images/I/51c1%2BwNe2tL.jpg",
										"main" : {
											"http://ecx.images-amazon.com/images/I/51c1%2BwNe2tL._SY355_.jpg" : [
													355, 355 ],
											"http://ecx.images-amazon.com/images/I/51c1%2BwNe2tL._SY450_.jpg" : [
													450, 450 ],
											"http://ecx.images-amazon.com/images/I/51c1%2BwNe2tL._SX425_.jpg" : [
													425, 425 ],
											"http://ecx.images-amazon.com/images/I/51c1%2BwNe2tL._SX466_.jpg" : [
													466, 466 ],
											"http://ecx.images-amazon.com/images/I/51c1%2BwNe2tL.jpg" : [
													500, 500 ]
										},
										"variant" : "PT01"
									} ]
						},
						'colorToAsin' : {
							'initial' : {}
						},
						'heroImage' : {
							'initial' : null
						},
						'holderRatio' : 1.0,
						'holderMaxHeight' : 700,
						'useStretchyImageFix' : true,
						'isAltImageAutoCycleEnabled' : false,
						'altImageAutoCycleInterval' : 0,
						'isEnhancedImageBlockSize' : false,
						'preloadingMainBlockForSoftlines' : false
					};
					A.trigger('P.AboveTheFold'); // trigger ATF event.
					return data;
				});

P.when('jQuery', 'cf').execute(function($, cf) {

});

P.when('A', 'jQuery', 'ImageBlockATF', 'cf')
		.register(
				'ImageBlockBTF',
				function(A, $, imageBlockATF, cf) {
					var data = {
						"indexToColor" : [ "initial" ],
						"burjImageBlock" : 0,
						"isSwatchHoverConsistent" : 1,
						"heroFocalPoint" : null,
						"visualDimensions" : [],
						"productGroupID" : "home_display_on_website",
						"thumbnailLeftAlignmentEnabled" : "1",
						"newVideoMissing" : 0,
						"useIV" : 1,
						"useClickZoom" : null,
						"useChildVideos" : 1,
						"numColors" : 1,
						"logMetrics" : 1,
						"defaultColor" : "initial",
						"airyConfig" : {
							"enableContinuousPlay" : "",
							"installFlashButtonText" : "Install Flash Player",
							"contentTitle" : null,
							"autoplayCutOffTimeSeconds" : null,
							"cssUrl" : "http://z-ecx.images-amazon.com/images/G/01/vap/video/airy2/prod/2.0.1283.0/css/beacon._CB289604497_.css",
							"ageGate" : {
								"monthNames" : [ "January", "February",
										"March", "April", "May", "June",
										"July", "August", "September",
										"October", "November", "December" ],
								"deniedPrompt" : "We're sorry. You are not old enough to watch this video.",
								"submitText" : "Submit",
								"prompt" : "This video is not intended for all audiences. What date were you born?"
							},
							"videoAds" : null,
							"videoUnsupportedPrompt" : "Sorry, this video is unsupported on this browser.",
							"desiredMode" : null,
							"swfUrl" : "http://g-ecx.images-amazon.com/images/G/01/vap/video/airy2/prod/2.0.1283.0/flash/AiryBasicRenderer._CB289604507_.swf",
							"isAutoplayEnabled" : null,
							"installFlashPrompt" : "Adobe Flash Player is required to watch this video.",
							"isLiveStream" : null,
							"regionCode" : "NA",
							"contentId" : null,
							"playbackErrorPrompt" : "Sorry, an error has occurred while attempting video playback. Please try again later.",
							"contentMinAge" : null,
							"isForesterTrackingDisabled" : null,
							"streamingUrls" : null,
							"parentId" : null,
							"foresterMetadataParams" : {
								"client" : "Dpx",
								"requestId" : "166AWQA84VXXFYVK056K",
								"marketplaceId" : "ATVPDKIKX0DER",
								"session" : "185-9089358-6905224",
								"method" : "Home.ImageBlock"
							},
							"jsUrl" : "http://z-ecx.images-amazon.com/images/G/01/vap/video/airy2/prod/2.0.1283.0/js/airy.skin._CB289604501_.js"
						},
						"showBiggerImmersiveView" : "",
						"mainImageMaxSizes" : null,
						"staticStrings" : {
							"playVideo" : "Click to play video",
							"rollOverToZoom" : "Roll over image to zoom in",
							"images" : "Images",
							"video" : "video",
							"clickToZoom" : "Click on image to zoom in",
							"touchToZoom" : "Touch the image to zoom in",
							"videos" : "Videos",
							"close" : "Close",
							"pleaseSelect" : "Please select",
							"clickToExpand" : "Click to open expanded view",
							"allMedia" : "All Media"
						},
						"notThumbnailClickImmersiveView" : 1,
						"gIsNewTwister" : 0,
						"title" : "Gifts & Decor Light Up Dolphin Sculpture Figurine Desk Table Figure",
						"ivRepresentativeAsin" : {},
						"useAltIngress" : 0,
						"mainImageSizes" : [ [ 355, 355 ], [ 450, 450 ],
								[ 425, 550 ], [ 466, 606 ], [ 522, 679 ] ],
						"switchDynImgDims" : 0,
						"views" : [ "ImageBlockMagnifierView",
								"ImageBlockAltImageView",
								"ImageBlockVideoView", "ImageBlockTwisterView",
								"ImageBlockImmersiveView" ],
						"isQuickview" : 0,
						"ipadVideoSizes" : [ [ 340, 444 ], [ 384, 500 ] ],
						"isSoftlinesEnhancedHoverZoom" : null,
						"colorToAsin" : {},
						"thumbExperimentEnabledValue" : 1,
						"showLITBOnClick" : 0,
						"videoSizes" : [ [ 342, 445 ], [ 384, 500 ] ],
						"stretchyGoodnessWidth" : [ 1280, 1440, 1640, 1800 ],
						"autoplayVideo" : 0,
						"hoverZoomIndicator" : "",
						"sitbReftag" : "",
						"useHoverZoom" : 1,
						"staticImages" : {
							"zoomOut" : "http://g-ecx.images-amazon.com/images/G/01/detail-page/cursors/zoom-out._CB184888738_.bmp",
							"hoverZoomIcon" : "http://g-ecx.images-amazon.com/images/G/01/img11/apparel/UX/DP/icon_zoom._CB138923886_.png",
							"zoomIn" : "http://g-ecx.images-amazon.com/images/G/01/detail-page/cursors/zoom-in._CB184888790_.bmp",
							"zoomLensBackground" : "http://g-ecx.images-amazon.com/images/G/01/apparel/rcxgs/tile._CB211431200_.gif",
							"videoThumbIcon" : "http://g-ecx.images-amazon.com/images/G/01/Quarterdeck/en_US/images/video._CB183716339_SS40_.gif",
							"spinner" : "http://g-ecx.images-amazon.com/images/G/01/ui/loadIndicators/loading-large_labeled._CB192238949_.gif",
							"zoomInCur" : "http://g-ecx.images-amazon.com/images/G/01/detail-page/cursors/zoomIn._CB323082799_.cur",
							"videoSWFPath" : "http://g-ecx.images-amazon.com/images/G/01/Quarterdeck/en_US/video/20110518115040892/Video._CB178668404_.swf",
							"arrow" : "http://g-ecx.images-amazon.com/images/G/01/javascripts/lib/popover/images/light/sprite-vertical-popover-arrow._CB186877868_.png",
							"zoomOutCur" : "http://g-ecx.images-amazon.com/images/G/01/detail-page/cursors/zoomOut._CB323082798_.cur"
						},
						"videos" : [],
						"isAUIPopoverEnabled" : "1",
						"gPreferChildVideos" : 1,
						"altsOnLeft" : 1,
						"useHoverZoomIpad" : "",
						"ivImageSetKeys" : {
							"initial" : 0
						},
						"isUDP" : 1,
						"alwaysIncludeVideo" : 1,
						"widths" : [ 1280, 1440, 1640, 1800 ],
						"maxAlts" : 7,
						"useChromelessVideoPlayer" : 0,
						"mainImageHeightPartitions" : null
					};
					data["customerImages"] = eval('[]');
					data["colorImages"] = {};
					data["heroImage"] = {};
					data["landingAsinColor"] = 'initial';
					data["shouldApplyResizeFix"] = false;

					return data;
				});