          //�ж�ͼƬ�Ƿ�������
          function IsImageLoaded()
          {
              var img=event.srcElement;

              if(img.src.indexOf("picture_place_holder.png") > 0)
              {
                  img.src = img.attributes['loadingsrc'].nodeValue;
              }
              else if(img.src.indexOf("ruanmei_webview_loadingimage.gif") > 0)
              {
              }
              else
              {
                   if(img.complete)
                   {
						//ios ���ͼƬʹ��
                       var imagesrc = img.attributes['originsrc'].nodeValue;
                       //document.location = imagesrc.replace("http://","ruanmeipic://");
                       ProxyClickPicture.clickImg(imagesrc);
                   }
                   else
                   {
                       //δ�������ʱ���ݲ��Ŵ�ͼƬ���Ժ�����޸�
                       //img.src = img.attributes['originsrc'].nodeValue;
                   }
              }
          }

          function imageload()
          {
              var img=event.srcElement;

              if(img.src.indexOf("ruanmei_webview_loadingimage.gif") > 0)
              {
                  img.src = img.attributes['originsrc'].nodeValue;
              }
              else
              {
              }
          }


