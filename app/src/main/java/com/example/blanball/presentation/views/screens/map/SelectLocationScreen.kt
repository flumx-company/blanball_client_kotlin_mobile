package com.example.blanball.presentation.views.screens.map

//@Composable
//fun SelectLocationScreen(
//    onCancelClicked: () -> Unit,
//    onSaveLocationClicked: () -> Unit,
//    eventLocationLatLng: MutableState<LatLng>,
//    listOfPlacesOfResidence: List<String>,
//
//) {
//    Box(
//        modifier = Modifier.padding()
//    ) {
//        Text(
//            text = stringResource(R.string.location),
//            fontSize = 16.sp,
//            lineHeight = 24.sp,
//            style = typography.h3,
//            fontWeight = FontWeight(700),
//            color = primaryDark,
//        )
//        Spacer(modifier = Modifier.size(16.dp))
//        CustomDropDownMenu(
//            labelResId = R.string.place_of_residence,
//            listItems =,
//            value =,
//            onValueChange =
//        )
//        Spacer(modifier = Modifier.size(16.dp))
//        CustomDropDownMenu(
//            labelResId = R.string.place_of_residence,
//            listItems =,
//            value =,
//            onValueChange =
//        )
//        Spacer(modifier = Modifier.size(12.dp))
//        Text(
//            text = stringResource(R.string.open_the_map),
//            fontSize = 12.sp,
//            lineHeight = 20.sp,
//            style = typography.h4,
//            fontWeight = FontWeight(400),
//            color = mainGreen,
//            textDecoration = TextDecoration.Underline,
//        )
//        Spacer(modifier = Modifier.size(12.dp))
//        SelectLocationWithGoogleMap(
//            eventLocationLatLng = eventLocationLatLng,
//            height = 244.dp,
//        )
//        Spacer(modifier = Modifier.size(16.dp))
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Box(
//                modifier = Modifier
//                    .padding(horizontal = 6.dp, vertical = 12.dp)
//                    .clip(shape = shapes.small)
//                    .clickable { onCancelClicked() }
//            ) {
//                Text(
//                    text = stringResource(R.string.cancel),
//                    fontSize = 14.sp,
//                    lineHeight = 20.sp,
//                    style = typography.h4,
//                    fontWeight = FontWeight(500),
//                    color = secondaryNavy,
//                    textAlign = TextAlign.Center,
//                )
//            }
//            Spacer(modifier = Modifier.weight(1f))
//            Box(
//                modifier = Modifier.padding(horizontal = 6.dp, vertical = 12.dp)
//                    .background(
//                        color = mainGreen,
//                        shape = shapes.small
//                    )
//                    .clickable { onSaveLocationClicked() },
//            ) {
//                Text(
//                    text = stringResource(R.string.save_the_location),
//                    fontSize = 15.sp,
//                    lineHeight = 24.sp,
//                    style = typography.h4,
//                    fontWeight = FontWeight(400),
//                    color = Color.White,
//                    textAlign = TextAlign.Center,
//                )
//            }
//        }
//    }
//}