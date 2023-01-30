package edu.robertconstantin.animeappcliient.feature_heroes.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import edu.robertconstantin.animeappcliient.core.ui.theme.LocalSpacing
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.util.PresentationConst.BASE_IMAGE_URL
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.util.PresentationConst.HERO_ITEM_HEIGHT

@Composable
fun HeroItem(
    imageLoader: ImageLoader,
    hero: HeroVO,
    onHeroFavoriteClick: (heroItem: HeroVO) -> Unit = {},
    onHeroDeleteClick: (heroItem: HeroVO) -> Unit = {},
    isFromFavorites: Boolean = false
) {

    val dimens = LocalSpacing.current
    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT.dp)
            .padding(dimens.spaceSmall)
            .clickable {

            },
        contentAlignment = Alignment.BottomStart
    ) {
        //Surface 1 for adding the image as background using surface.
        Surface(shape = RoundedCornerShape(size = dimens.spaceSmall)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(
                    model = BASE_IMAGE_URL.plus(hero.image),
                    imageLoader = imageLoader
                ),
                contentDescription = "Hero Item",
                contentScale = ContentScale.Crop
            )
            if (!isFromFavorites){
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(dimens.spaceSmall)
                        .clickable {
                            onHeroFavoriteClick(hero)
                        },
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite icon",
                    colorFilter = if (hero.isAddedToFavorites)
                        ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    else ColorFilter.tint(Color.White)
                )
            } else {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(dimens.spaceSmall)
                        .clickable {
                            onHeroDeleteClick(hero)
                        },
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Favorite icon",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)

                )
            }

        }
        //Surface 2 for description.
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = dimens.spaceSmall,
                bottomEnd = dimens.spaceSmall
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = dimens.spaceSmall)
            ) {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.padding(top = dimens.spaceSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }


            }

        }

    }
}