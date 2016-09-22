package com.example.coder.jiandan_md.refresh;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.HTMLElement;
import com.example.coder.jiandan_md.util.ImageLoadProxy;
import com.example.coder.jiandan_md.widget.ShowMaxImageView;

import java.util.List;

/**
 * Created by coder on 16/9/19.
 */
public class RefreshDetailContent {

    public static void addInView(LinearLayout layout , List<HTMLElement> elements) {

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams textParams =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        if (elements.get(0).getType() == HTMLElement.ElementType.Image) {

            elements.remove(0);
        }

        for (HTMLElement element : elements) {

            if (element.getType() == HTMLElement.ElementType.Text) {

                textParams.setMargins(0,5,0,5);

                TextView textView = new TextView(layout.getContext());

                textView.setLayoutParams(textParams);

                textView.setTextSize(15);

                textView.setLineSpacing(1,1.4f);

                textView.setText(Html.fromHtml(element.getTextBuffer().toString()));

                textView.setClickable(true);

                textView.setMovementMethod(LinkMovementMethod.getInstance());

                textView.setTextColor(layout.getResources().getColor(android.R.color.black));

                layout.addView(textView,textParams);

            } else if (element.getType() == HTMLElement.ElementType.Image) {

                imageParams.setMargins(0,10,0,10);

                ShowMaxImageView imageView = new ShowMaxImageView(layout.getContext());

                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                imageView.setLayoutParams(imageParams);

                layout.addView(imageView,imageParams);

                ImageLoadProxy.displayImageWithLoadingPicture(element.getLink(),imageView,R.drawable.ic_loading_large);


            } else if (element.getType() == HTMLElement.ElementType.Heade) {

                textParams.setMargins(0,5,0,5);

                TextView textView = new TextView(layout.getContext());

                textView.setLayoutParams(textParams);

                textView.setTextSize(17);

                textView.setLineSpacing(1,1.4f);

                textView.getPaint().setFakeBoldText(true);

                textView.setText(element.getTextBuffer().toString());

                textView.setTextColor(layout.getResources().getColor(android.R.color.black));

                layout.addView(textView,textParams);
            }

        }

    }
}
